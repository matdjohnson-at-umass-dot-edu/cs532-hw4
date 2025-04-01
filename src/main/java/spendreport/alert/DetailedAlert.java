package spendreport.alert;

import spendreport.data.DetailedTransaction;

import java.util.Objects;

@SuppressWarnings("unused")
public final class DetailedAlert { // update class name

    private static final long serialVersionUID = 1L;
    private long id;dd
    private long accountId; // update id name to reflect actual id type
    private long timestamp;
    private double amount;
    private String postalCode; // add postal code

    public DetailedAlert() { } // add no-arg constructor for serialization compatability

    public DetailedAlert(DetailedTransaction detailedTransaction) { // add source data constructor to access postal code information
        this.id = detailedTransaction.getAccountId();
        this.accountId = detailedTransaction.getAccountId();
        this.timestamp = detailedTransaction.getTimestamp();
        this.amount = detailedTransaction.getAmount();
        this.postalCode = detailedTransaction.getPostalCode();
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPostalCode() { // add postal code getter and setter
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
        DetailedAlert alert = (DetailedAlert) o;
        return getAccountId() == alert.getAccountId() &&
               getTimestamp() == alert.getTimestamp() &&
               getAmount() == alert.getAmount() &&
               getPostalCode().equals(alert.getPostalCode()); // clean up equality statement
    }

    @Override
    public int hashCode() { // update hash code source data
        return Objects.hash(getAccountId(), getTimestamp(), getAmount(), getPostalCode());
    }

    @Override
    public String toString() {
        return "DetailedAlert{" +
                "accountId=" + getAccountId() + ", " +
                "timestamp=" + getTimestamp() + ", " +
                "amount=" + getAmount() + ", " +
                "postalCode=" + getPostalCode() +
                "}"; // add postal code to toString
    }

}
