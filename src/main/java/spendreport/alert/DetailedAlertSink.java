package spendreport.alert;

import org.apache.flink.annotation.PublicEvolving;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PublicEvolving
@SuppressWarnings("unused")
public class DetailedAlertSink implements SinkFunction<DetailedAlert> { // update class name, generic type
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(DetailedAlertSink.class);

    @Override
    public void invoke(DetailedAlert value, SinkFunction.Context context) { // retain invoke method with updated signature
        LOG.info(value.toString());
    }

}
