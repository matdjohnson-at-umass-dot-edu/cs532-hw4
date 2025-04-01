package spendreport.data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.SplittableRandom;

final class DetailedTransactionIterator implements Iterator<DetailedTransaction>, Serializable { // keep class package private and final

    private static final long serialVersionUID = 1L;


    private final boolean isStatic;

    private int staticIteratorIndex = 0;

    private long dynamicTimestampValue = 0;

    static DetailedTransactionIterator staticUnbounded() { // add method for test data iterator instantiation
        return new DetailedTransactionIterator(true);
    }

    static DetailedTransactionIterator dynamicUnbounded() { // add method for "live" data iterator instantiation
        return new DetailedTransactionIterator(false);
    }

    private DetailedTransactionIterator(boolean isStatic) { // keep constructor private
        this.isStatic = isStatic;
    }

    @Override
    public boolean hasNext() {
        return true; // both static and dynamic generators are unbounded, return true for all cases
    }

    @Override
    public DetailedTransaction next() {
        if (isStatic) { // conditionally generate source data
            return generateStatic();
        } else {
            return generateDynamic();
        }
    }

    private DetailedTransaction generateDynamic() { // add dynamic source data generation
        SplittableRandom splittableRandom = new SplittableRandom();
        DetailedTransaction detailedTransaction = new DetailedTransaction(
                accountIds[splittableRandom.nextInt(0, 5)],         // account id randomly selected from predefined array
                dynamicTimestampValue,                                           // timestamp incremented starting from 0
                postalCodes[splittableRandom.nextInt(0, 3)],        // postal code randomly selected from predefined array
                splittableRandom.nextDouble(amountLowerBound, amountUpperBound)  // amount sampled randomly from predfined range
        );
        dynamicTimestampValue = dynamicTimestampValue + 1; // increment timestamp value
        return detailedTransaction;
    }

    final private int[] accountIds = { 1, 2, 3, 4, 5 };                 // ids as defined in assignment
    final private String[] postalCodes = { "01003", "02115", "78712" }; // postal codes as defined in assignment
    final private double amountLowerBound = 0.0;                        // amount lower bound as defined in assignment
    final private double amountUpperBound = 1000.0;                     // amount upper bound as defined in assignment

    private DetailedTransaction generateStatic() { // retrieve from prepopulated data array
        if (staticIteratorIndex >= data.size()) { // wrap on data array index
            staticIteratorIndex = 0;
        }
        DetailedTransaction detailedTransaction = data.get(staticIteratorIndex);
        staticIteratorIndex = staticIteratorIndex + 1;
        return detailedTransaction;
    }

    private static List<DetailedTransaction> data = // update static data source type, add postal code to data
            Arrays.asList(
                    new DetailedTransaction(1, 0L, "01003", 188.23),
                    new DetailedTransaction(2, 0L, "02115", 374.79),
                    new DetailedTransaction(3, 0L, "78712", 112.15),
                    new DetailedTransaction(4, 0L, "01003", 478.75),
                    new DetailedTransaction(5, 0L, "78712", 208.85),
                    new DetailedTransaction(1, 0L, "01003", 379.64),
                    new DetailedTransaction(2, 0L, "02115", 351.44),
                    new DetailedTransaction(3, 0L, "78712", 320.75),
                    new DetailedTransaction(4, 0L, "02115", 259.42),
                    new DetailedTransaction(5, 0L, "78712", 273.44),
                    new DetailedTransaction(1, 0L, "01003", 267.25),
                    new DetailedTransaction(2, 0L, "02115", 397.15),
                    new DetailedTransaction(3, 0L, "78712", 0.219),
                    new DetailedTransaction(4, 0L, "01003", 231.94),
                    new DetailedTransaction(5, 0L, "78712", 384.73),
                    new DetailedTransaction(1, 0L, "01003", 419.62),
                    new DetailedTransaction(2, 0L, "02115", 412.91),
                    new DetailedTransaction(3, 0L, "78712", 0.77),
                    new DetailedTransaction(4, 0L, "02115", 22.10),
                    new DetailedTransaction(5, 0L, "78712", 377.54),
                    new DetailedTransaction(1, 0L, "01003", 375.44),
                    new DetailedTransaction(2, 0L, "02115", 230.18),
                    new DetailedTransaction(3, 0L, "78712", 0.80),
                    new DetailedTransaction(4, 0L, "01003", 350.89),
                    new DetailedTransaction(5, 0L, "78712", 127.55),
                    new DetailedTransaction(1, 0L, "01003", 483.91),
                    new DetailedTransaction(2, 0L, "02115", 228.22),
                    new DetailedTransaction(3, 0L, "78712", 871.15),
                    new DetailedTransaction(4, 0L, "02115", 64.19),
                    new DetailedTransaction(5, 0L, "78712", 79.43),
                    new DetailedTransaction(1, 0L, "01003", 56.12),
                    new DetailedTransaction(2, 0L, "02115", 256.48),
                    new DetailedTransaction(3, 0L, "78712", 148.16),
                    new DetailedTransaction(4, 0L, "01003", 199.95),
                    new DetailedTransaction(5, 0L, "78712", 252.37),
                    new DetailedTransaction(1, 0L, "01003", 274.73),
                    new DetailedTransaction(2, 0L, "02115", 473.54),
                    new DetailedTransaction(3, 0L, "78712", 119.92),
                    new DetailedTransaction(4, 0L, "02115", 323.59),
                    new DetailedTransaction(5, 0L, "02115", 353.16),
                    new DetailedTransaction(1, 0L, "01003", 211.90),
                    new DetailedTransaction(2, 0L, "02115", 280.93),
                    new DetailedTransaction(3, 0L, "78712", 347.89),
                    new DetailedTransaction(4, 0L, "01003", 459.86),
                    new DetailedTransaction(5, 0L, "02115", 82.31),
                    new DetailedTransaction(1, 0L, "01003", 373.26),
                    new DetailedTransaction(2, 0L, "02115", 479.83),
                    new DetailedTransaction(3, 0L, "78712", 454.25),
                    new DetailedTransaction(4, 0L, "02115", 83.64),
                    new DetailedTransaction(5, 0L, "78712", 292.44));
}
