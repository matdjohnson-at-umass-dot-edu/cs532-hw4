package spendreport;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import spendreport.alert.DetailedAlert;
import spendreport.alert.DetailedAlertSink;
import spendreport.data.DetailedTransaction;
import spendreport.data.DetailedTransactionSource;


public class DetailedFraudDetectionJob { // update class name

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        boolean generateStatically = false; // add variable for tracking test or live data generation

        DataStream<DetailedTransaction> transactions = env  // update source data type
                .addSource(new DetailedTransactionSource(generateStatically)) // update data source
                .name("transactions");

        DataStream<DetailedAlert> alerts = transactions // update alert data type
                .keyBy(DetailedTransaction::getAccountId) // update source data type
                .process(new DetailedFraudDetector()) // update KeyedProcessFunction class
                .name("fraud-detector");

        alerts
                .addSink(new DetailedAlertSink()) // update sink function class
                .name("send-alerts");

        env.execute("Fraud Detection");
    }

}
