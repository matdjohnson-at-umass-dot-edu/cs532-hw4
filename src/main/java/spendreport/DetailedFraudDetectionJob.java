package spendreport;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import spendreport.alert.DetailedAlert;
import spendreport.alert.DetailedAlertSink;
import spendreport.data.DetailedTransaction;
import spendreport.data.DetailedTransactionSource;


public class DetailedFraudDetectionJob {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        boolean generateStatically = false;

        DataStream<DetailedTransaction> transactions = env
                .addSource(new DetailedTransactionSource(generateStatically))
                .name("transactions");

        DataStream<DetailedAlert> alerts = transactions
                .keyBy(DetailedTransaction::getAccountId)
                .process(new DetailedFraudDetector())
                .name("fraud-detector");

        alerts
                .addSink(new DetailedAlertSink())
                .name("send-alerts");

        env.execute("Fraud Detection");
    }

}
