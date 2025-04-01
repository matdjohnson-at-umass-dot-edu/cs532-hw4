package spendreport.data;

import spendreport.alert.DetailedAlert;

import java.util.Objects;

public final class DetailedTransaction {

    private static final long serialVersionUID = 1L;

    private long accountId;
    private long timestamp;
    private double amount;
    private String postalCode;

    public DetailedTransaction() {}

    public DetailedTransaction(long accountId, long timestamp, double amount, String postalCode) {
        this.accountId = accountId;
        this.timestamp = timestamp;
        this.amount = amount;
        this.postalCode = postalCode;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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
                getAmount() == detailedTransaction.getAmount() &&
                getPostalCode().equals(detailedTransaction.getPostalCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccountId(), getTimestamp(), getAmount(), getPostalCode());
    }

    @Override
    public String toString() {
        return "DetailedTransaction{" +
                "accountId=" + getAccountId() + ", " +
                "timestamp=" + getTimestamp() + ", " +
                "amount=" + getAmount() + ", " +
                "postalCode=" + getPostalCode() +
                "}";
    }

}
