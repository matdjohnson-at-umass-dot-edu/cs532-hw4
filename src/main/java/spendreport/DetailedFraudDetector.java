package spendreport;

import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;
import spendreport.alert.DetailedAlert;
import spendreport.data.DetailedTransaction;

public class DetailedFraudDetector
        extends KeyedProcessFunction<Long, DetailedTransaction, DetailedAlert> { // update class name, generics

    private static final long serialVersionUID = 1L;

    private static final double SMALL_AMOUNT = 10.00; // update small amount from 1 to 10
    private static final double LARGE_AMOUNT = 500.00;

    private transient ValueState<Boolean> lastTxSmall; // retain transaction tracking ValueState
    private transient ValueState<String> lastTxPostalCode; // add postal code tracking ValueState

    @Override
    public void open(Configuration configuration) {
        lastTxSmall = getRuntimeContext().getState(
                new ValueStateDescriptor<>("lastTxSmall", Types.BOOLEAN)
        ); // retain transaction tracking ValueState
        lastTxPostalCode = getRuntimeContext().getState(
                new ValueStateDescriptor<>("nextTxThreshholdTime", Types.STRING)
        ); // add postal code tracking ValueState
    }

    @Override
    public void processElement(
            DetailedTransaction detailedTransaction, // update source data type
            Context context,
            Collector<DetailedAlert> collector // update alert data type
    ) throws Exception {
        Boolean lastTxSmallForIter = lastTxSmall.value(); // retain transaction value state retrieval
        String lastTxPostalCodeForIter = lastTxPostalCode.value(); // add postal code value state retrieval

        if (lastTxSmallForIter != null
                && lastTxSmallForIter == Boolean.TRUE
                && detailedTransaction != null
                && detailedTransaction.getAmount() >= LARGE_AMOUNT
                && lastTxPostalCodeForIter != null
                && lastTxPostalCodeForIter.equals(detailedTransaction.getPostalCode())
        ) {  // add null safety checks, add conditional for postal code
            DetailedAlert detailedAlert = new DetailedAlert(detailedTransaction); // altered alert creation
            collector.collect(detailedAlert); // retain alert collection
        }

        lastTxSmall.clear(); // clear value state variable
        lastTxPostalCode.clear();

        if (detailedTransaction != null) {
            lastTxSmall.update(detailedTransaction.getAmount() <= SMALL_AMOUNT); // use non-null value for tracking previous transaction amount
            lastTxPostalCode.update(detailedTransaction.getPostalCode()); // add population of postal code tracker
        }
    }

}
