package spendreport.alert;

import spendreport.data.DetailedTransaction;

import java.util.Objects;

@SuppressWarnings("unused")
public final class DetailedAlert { // update class name

    private static final long serialVersionUID = 1L;
    private long id;
    private long accountId; // update id name to reflect actual id type
    private long timestamp;
    private String postalCode; // add postal code
    private double amount;

    public DetailedAlert() { } // add no-arg constructor for serialization compatability

    public DetailedAlert(DetailedTransaction detailedTransaction) { // add source data constructor to access postal code information
        this.id = detailedTransaction.getAccountId();
        this.accountId = detailedTransaction.getAccountId();
        this.timestamp = detailedTransaction.getTimestamp();
        this.postalCode = detailedTransaction.getPostalCode();
        this.amount = detailedTransaction.getAmount();
    }

    public long getId() { // retain id for serialization support
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getPostalCode() { // add postal code getter and setter
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
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
        DetailedAlert alert = (DetailedAlert) o;
        return getAccountId() == alert.getAccountId() &&
                getTimestamp() == alert.getTimestamp() &&
                getPostalCode().equals(alert.getPostalCode()) &&
                getAmount() == alert.getAmount(); // clean up equality statement
    }

    @Override
    public int hashCode() { // update hash code source data
        return Objects.hash(getAccountId(), getTimestamp(), getPostalCode(), getAmount());
    }

    @Override
    public String toString() {
        return "DetailedAlert{" +
                "accountId=" + getAccountId() + ", " +
                "timestamp=" + getTimestamp() + ", " +
                "postalCode=" + getPostalCode() + ", " + // postal code for globalization
                "amount=" + getAmount() +
                "}"; // add postal code to toString
    }

}
