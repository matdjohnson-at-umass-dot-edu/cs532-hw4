package spendreport.data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.SplittableRandom;

final class DetailedTransactionIterator implements Iterator<DetailedTransaction>, Serializable {

    private static final long serialVersionUID = 1L;


    private final boolean isStatic;

    private int staticIteratorIndex = 0;

    private long dynamicTimestampValue = 0;

    static DetailedTransactionIterator staticUnbounded() {
        return new DetailedTransactionIterator(true);
    }

    static DetailedTransactionIterator dynamicUnbounded() {
        return new DetailedTransactionIterator(false);
    }

    private DetailedTransactionIterator(boolean isStatic) {
        this.isStatic = isStatic;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public DetailedTransaction next() {
        if (isStatic) {
            return generateStatic();
        } else {
            return generateDynamic();
        }
    }

    private DetailedTransaction generateDynamic() {
        SplittableRandom splittableRandom = new SplittableRandom();
        DetailedTransaction detailedTransaction = new DetailedTransaction(
                accountIds[splittableRandom.nextInt(0, 5)],
                dynamicTimestampValue,
                splittableRandom.nextDouble(amountLowerBound, amountUpperBound),
                postalCodes[splittableRandom.nextInt(0, 3)]
        );
        dynamicTimestampValue = dynamicTimestampValue + 1;
        return detailedTransaction;
    }

    final private int[] accountIds = { 1, 2, 3, 4, 5 };
    final private String[] postalCodes = { "01003", "02115", "78712" };
    final private double amountLowerBound = 0.0;
    final private double amountUpperBound = 1000.0;

    private DetailedTransaction generateStatic() {
        if (staticIteratorIndex >= data.size()) {
            staticIteratorIndex = 0;
        }
        DetailedTransaction detailedTransaction = data.get(staticIteratorIndex);
        staticIteratorIndex = staticIteratorIndex + 1;
        return detailedTransaction;
    }

    private static List<DetailedTransaction> data =
            Arrays.asList(
                    new DetailedTransaction(1, 0L, 188.23, "01003"),
                    new DetailedTransaction(2, 0L, 374.79, "02115"),
                    new DetailedTransaction(3, 0L, 112.15, "78712"),
                    new DetailedTransaction(4, 0L, 478.75, "01003"),
                    new DetailedTransaction(5, 0L, 208.85, "78712"),
                    new DetailedTransaction(1, 0L, 379.64, "01003"),
                    new DetailedTransaction(2, 0L, 351.44, "02115"),
                    new DetailedTransaction(3, 0L, 320.75, "78712"),
                    new DetailedTransaction(4, 0L, 259.42, "02115"),
                    new DetailedTransaction(5, 0L, 273.44, "78712"),
                    new DetailedTransaction(1, 0L, 267.25, "01003"),
                    new DetailedTransaction(2, 0L, 397.15, "02115"),
                    new DetailedTransaction(3, 0L, 0.219, "78712"),
                    new DetailedTransaction(4, 0L, 231.94, "01003"),
                    new DetailedTransaction(5, 0L, 384.73, "78712"),
                    new DetailedTransaction(1, 0L, 419.62, "01003"),
                    new DetailedTransaction(2, 0L, 412.91, "02115"),
                    new DetailedTransaction(3, 0L, 0.77, "78712"),
                    new DetailedTransaction(4, 0L, 22.10, "02115"),
                    new DetailedTransaction(5, 0L, 377.54, "78712"),
                    new DetailedTransaction(1, 0L, 375.44, "01003"),
                    new DetailedTransaction(2, 0L, 230.18, "02115"),
                    new DetailedTransaction(3, 0L, 0.80, "78712"),
                    new DetailedTransaction(4, 0L, 350.89, "01003"),
                    new DetailedTransaction(5, 0L, 127.55, "78712"),
                    new DetailedTransaction(1, 0L, 483.91, "01003"),
                    new DetailedTransaction(2, 0L, 228.22, "02115"),
                    new DetailedTransaction(3, 0L, 871.15, "78712"),
                    new DetailedTransaction(4, 0L, 64.19, "02115"),
                    new DetailedTransaction(5, 0L, 79.43, "78712"),
                    new DetailedTransaction(1, 0L, 56.12, "01003"),
                    new DetailedTransaction(2, 0L, 256.48, "02115"),
                    new DetailedTransaction(3, 0L, 148.16, "78712"),
                    new DetailedTransaction(4, 0L, 199.95, "01003"),
                    new DetailedTransaction(5, 0L, 252.37, "78712"),
                    new DetailedTransaction(1, 0L, 274.73, "01003"),
                    new DetailedTransaction(2, 0L, 473.54, "02115"),
                    new DetailedTransaction(3, 0L, 119.92, "78712"),
                    new DetailedTransaction(4, 0L, 323.59, "02115"),
                    new DetailedTransaction(5, 0L, 353.16, "02115"),
                    new DetailedTransaction(1, 0L, 211.90, "01003"),
                    new DetailedTransaction(2, 0L, 280.93, "02115"),
                    new DetailedTransaction(3, 0L, 347.89, "78712"),
                    new DetailedTransaction(4, 0L, 459.86, "01003"),
                    new DetailedTransaction(5, 0L, 82.31, "02115"),
                    new DetailedTransaction(1, 0L, 373.26, "01003"),
                    new DetailedTransaction(2, 0L, 479.83, "02115"),
                    new DetailedTransaction(3, 0L, 454.25, "78712"),
                    new DetailedTransaction(4, 0L, 83.64, "02115"),
                    new DetailedTransaction(5, 0L, 292.44, "78712"));
}
