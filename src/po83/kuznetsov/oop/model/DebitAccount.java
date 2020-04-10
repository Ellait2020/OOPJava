package po83.kuznetsov.oop.model;

public class DebitAccount extends AbstractAccount implements Cloneable {

    public DebitAccount(String number, double balance) {
        super(number, balance);
    }

    public DebitAccount() {
        super("", 0);
    }

    @Override
    public String toString() {
        return "Debit Account\n" +
                "number:" + getNumber() + " balance:" + getBalance();
    }

    @Override
    public int hashCode() {
        return getNumber().hashCode()*String.format("%f", getBalance()).hashCode()&53;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if(o!=null){
            result = o.getClass() == this.getClass();
            if (result) {
                result = this.getNumber().equals(((DebitAccount) o).getNumber()) && this.getBalance() == ((DebitAccount) o).getBalance();
            }
        }
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}



