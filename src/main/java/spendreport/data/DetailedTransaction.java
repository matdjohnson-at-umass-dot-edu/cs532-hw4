package spendreport.data;

import java.util.Objects;

public final class DetailedTransaction { // update class name

    private static final long serialVersionUID = 1L;

    private long accountId;
    private long timestamp;
    private String postalCode; // add postal code
    private double amount;

    public DetailedTransaction() {}

    public DetailedTransaction(long accountId, long timestamp, String postalCode, double amount) { // add postal code to constructor
        this.accountId = accountId;
        this.timestamp = timestamp;
        this.postalCode = postalCode;
        this.amount = amount;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPostalCode() { // add postal code getter
        return postalCode;
    }

    public void setPostalCode(String postalCode) { // add postal code setter
        this.postalCode = postalCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DetailedTransaction detailedTransaction = (DetailedTransaction) o;
        return getAccountId() == detailedTransaction.getAccountId() &&
                getTimestamp() == detailedTransaction.getTimestamp() &&
                getPostalCode().equals(detailedTransaction.getPostalCode()) &&
                getAmount() == detailedTransaction.getAmount(); // clean up equality statement
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccountId(), getTimestamp(), getPostalCode(), getAmount()); // add postal code to hashCode
    }

    @Override
    public String toString() {
        return "DetailedTransaction{" +
                "accountId=" + getAccountId() + ", " +
                "timestamp=" + getTimestamp() + ", " +
                "postalCode=" + getPostalCode() + ", " +
                "amount=" + getAmount() +
                "}"; // add postal code to toString
    }

}
