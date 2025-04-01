package spendreport.alert;

import spendreport.data.DetailedTransaction;

import java.util.Objects;

@SuppressWarnings("unused")
public final class DetailedAlert {

    private static final long serialVersionUID = 1L;

    private long id;
    private long accountId;
    private long timestamp;
    private double amount;
    private String postalCode;

    public DetailedAlert() {

    }

    public DetailedAlert(DetailedTransaction detailedTransaction) {
        this.id = detailedTransaction.getAccountId();
        this.accountId = detailedTransaction.getAccountId();
        this.timestamp = detailedTransaction.getTimestamp();
        this.amount = detailedTransaction.getAmount();
        this.postalCode = detailedTransaction.getPostalCode();
    }

    public long getId() {
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
        DetailedAlert alert = (DetailedAlert) o;
        return getAccountId() == alert.getAccountId() &&
               getTimestamp() == alert.getTimestamp() &&
               getAmount() == alert.getAmount() &&
               getPostalCode().equals(alert.getPostalCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(toString());
    }

    @Override
    public String toString() {
        return "DetailedAlert{" +
                "accountId=" + getAccountId() + ", " +
                "timestamp=" + getTimestamp() + ", " +
                "amount=" + getAmount() + ", " +
                "postalCode=" + getPostalCode() +
                "}";
    }

}
