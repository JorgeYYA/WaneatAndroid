package florida.com.waneat.Models;

/**
 * Created by sergiomoreno on 8/2/18.
 */

public class CreditCard {
    private String creditCardNumber;
    private String creditCardHolderName;
    private String creditCardMonthDate;
    private String creditCardYearDate;
    private int creditCardCvc;

    public CreditCard(String creditCardNumber, String creditCardHolderName, String creditCardMonthDate, String creditCardYearDate, int creditCardCvc) {
        this.creditCardNumber = creditCardNumber;
        this.creditCardHolderName = creditCardHolderName;
        this.creditCardMonthDate = creditCardMonthDate;
        this.creditCardYearDate = creditCardYearDate;
        this.creditCardCvc = creditCardCvc;
    }

    public CreditCard (){}

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardHolderName() {
        return creditCardHolderName;
    }

    public void setCreditCardHolderName(String creditCardHolderName) {
        this.creditCardHolderName = creditCardHolderName;
    }

    public String getCreditCardMonthDate() {
        return creditCardMonthDate;
    }

    public void setCreditCardMonthDate(String creditCardMonthDate) {
        this.creditCardMonthDate = creditCardMonthDate;
    }

    public String getCreditCardYearDate() {
        return creditCardYearDate;
    }

    public void setCreditCardYearDate(String creditCardYearDate) {
        this.creditCardYearDate = creditCardYearDate;
    }

    public int getCreditCardCvc() {
        return creditCardCvc;
    }

    public void setCreditCardCvc(int creditCardCvc) {
        this.creditCardCvc = creditCardCvc;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "creditCardNumber='" + creditCardNumber + '\'' +
                ", creditCardHolderName='" + creditCardHolderName + '\'' +
                ", creditCardMonthDate='" + creditCardMonthDate + '\'' +
                ", creditCardYearDate='" + creditCardYearDate + '\'' +
                ", creditCardCvc=" + creditCardCvc +
                '}';
    }
}
