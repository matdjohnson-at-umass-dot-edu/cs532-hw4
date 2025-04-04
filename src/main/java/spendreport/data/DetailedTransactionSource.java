package spendreport.data;

import org.apache.flink.annotation.Public;
import org.apache.flink.streaming.api.functions.source.FromIteratorFunction;

import java.io.Serializable;
import java.util.Iterator;

@Public
public class DetailedTransactionSource extends FromIteratorFunction<DetailedTransaction> {

    private static final long serialVersionUID = 1L;

    public DetailedTransactionSource(boolean isStatic) { // add option for static or dynamic source data generation
        super(
                new DetailedTransactionSource.RateLimitedIterator<>(
                        isStatic ?
                                DetailedTransactionIterator.staticUnbounded() :
                                DetailedTransactionIterator.dynamicUnbounded()
                )
        );
    }

    private static class RateLimitedIterator<T> implements Iterator<T>, Serializable { // iterator as from sample code
        private static final long serialVersionUID = 1L;
        private final Iterator<T> inner;

        private RateLimitedIterator(Iterator<T> inner) {
            this.inner = inner;
        }

        @Override
        public boolean hasNext() {
            return inner.hasNext();
        }

        @Override
        public T next() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return inner.next();
        }
    }

}
