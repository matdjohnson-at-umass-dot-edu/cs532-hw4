package spendreport;

import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;
import spendreport.alert.DetailedAlert;
import spendreport.data.DetailedTransaction;

public class DetailedFraudDetector extends KeyedProcessFunction<Long, DetailedTransaction, DetailedAlert> {

    private static final long serialVersionUID = 1L;

    private static final double SMALL_AMOUNT = 10.00;
    private static final double LARGE_AMOUNT = 500.00;

    private transient ValueState<Boolean> lastTxSmall;
    private transient ValueState<String> lastTxPostalCode;

    @Override
    public void open(Configuration configuration) {
        lastTxSmall = getRuntimeContext().getState(
                new ValueStateDescriptor<>("lastTxSmall", Types.BOOLEAN)
        );
        lastTxPostalCode = getRuntimeContext().getState(
                new ValueStateDescriptor<>("nextTxThreshholdTime", Types.STRING)
        );
    }

    @Override
    public void processElement(
            DetailedTransaction detailedTransaction,
            Context context,
            Collector<DetailedAlert> collector
    ) throws Exception {
        Boolean lastTxSmallForIter = lastTxSmall.value();
        String lastTxPostalCodeForIter = lastTxPostalCode.value();

        if (lastTxSmallForIter != null
                && lastTxSmallForIter == Boolean.TRUE
                && detailedTransaction != null
                && detailedTransaction.getAmount() >= LARGE_AMOUNT
                && lastTxPostalCodeForIter != null
                && lastTxPostalCodeForIter.equals(detailedTransaction.getPostalCode())
        ) {
            DetailedAlert detailedAlert = new DetailedAlert(detailedTransaction);
            collector.collect(detailedAlert);
        }

        lastTxSmall.clear();
        lastTxPostalCode.clear();

        if (detailedTransaction != null) {
            lastTxSmall.update(detailedTransaction.getAmount() <= SMALL_AMOUNT);
            lastTxPostalCode.update(detailedTransaction.getPostalCode());
        }
    }

}
