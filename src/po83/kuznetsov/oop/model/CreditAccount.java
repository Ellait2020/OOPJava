package po83.kuznetsov.oop.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.regex.Pattern;

public class CreditAccount extends AbstractAccount implements  Credit {
    private double AnnualPercentageRate;

    public CreditAccount(String number, LocalDate expirationDate)
    {
        super(number,expirationDate);
        AnnualPercentageRate = 30;
    }

    public CreditAccount(String number, double balance,LocalDate creationDate,LocalDate expirationDate,double AnnualPercentageRate) {
        super(number, balance,creationDate,expirationDate);
        setAnnualPercentageRate(AnnualPercentageRate);
    }

    public double getAnnualPercentageRate() {
        return AnnualPercentageRate;
    }

    public void setAnnualPercentageRate(double AnnualPercentageRate) {
        this.AnnualPercentageRate = AnnualPercentageRate;
    }

    @Override
    public double getNextPaymentValue() {
        double result = getBalance() * (1 + getAnnualPercentageRate() *
                Period.between(LocalDate.now(), getExpirationDate()).getYears())
                / monthsQuantityBeforeExpiration();
        return result < 0 ? -result : result;
}

    @Override
    public LocalDate getNextPaymentDate() {
        LocalDate result = LocalDate.now();

        if (LocalDate.now().getDayOfMonth() >= 25) {
            result = result.plusMonths(1);
        }
        result.withDayOfMonth(25);

        return result;
    }

    @Override
    public String toString() {
        return "Credit account - " + "number: " + getNumber() + " balance: " + getBalance() +
                " Annual Percentage Rate: " + AnnualPercentageRate + " CreationDate: " + getCreationDate().toString() +
                " ExpirationDate: " + getExpirationDate().toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode() & 71 & String.format("%f", AnnualPercentageRate).hashCode() &
                getCreationDate().hashCode() & getExpirationDate().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return ((o.getClass() == this.getClass()) &&
                (getNumber().equals(((CreditAccount)o).getNumber())) &&
                (getBalance() == (((CreditAccount)o).getBalance())) &&
                (getAnnualPercentageRate() == (((CreditAccount)o).getAnnualPercentageRate())) &&
                getCreationDate().equals(((CreditAccount)o).getCreationDate()) &&
                getExpirationDate().equals(((CreditAccount)o).getExpirationDate()));
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean isNumberNotFormatted(String accountNumber) {
        Objects.requireNonNull(accountNumber, "Номер пустой");
        return !Pattern.matches("^4[45]\\d{3}810\\d(?!0{4})\\d{4}(?!0{7})\\d{7}$", accountNumber);
    }

}