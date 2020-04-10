package po83.kuznetsov.oop.model;

public class CreditAccount extends AbstractAccount implements  Credit, Cloneable {
    private double AnnualPercentageRate;

    public CreditAccount()
    {
        super("",0);
        AnnualPercentageRate = 30;
    }

    public CreditAccount(String number, double balance, double AnnualPercentageRate) {
        super(number, balance);
        setAnnualPercentageRate(AnnualPercentageRate);
    }

    public double getAnnualPercentageRate() {
        return AnnualPercentageRate;
    }

    public void setAnnualPercentageRate(double AnnualPercentageRate) {
        this.AnnualPercentageRate = AnnualPercentageRate;
    }

    @Override
    public String toString() {
        return "Credit account - " + "number: " + getNumber()  + " balance: " + getBalance() + " Annual Percentage Rate: " + AnnualPercentageRate;
    }

    @Override
    public int hashCode() {
            return super.hashCode() & 71 & String.format("%f", AnnualPercentageRate).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return ((o.getClass() == this.getClass()) &&
                (getNumber().equals(((CreditAccount)o).getNumber())) &&
                (getBalance() == (((CreditAccount)o).getBalance())) &&
                (getAnnualPercentageRate() == (((CreditAccount)o).getAnnualPercentageRate())));
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}