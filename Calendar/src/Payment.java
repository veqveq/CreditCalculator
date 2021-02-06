import java.util.Date;

public class Payment {
    private int number;
    private Date paymentDate;
    private float amount;

    public Payment(int number, Date paymentDate, float amount) {
        this.number = number;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }

    public Payment() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
