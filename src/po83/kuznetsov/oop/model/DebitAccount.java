package po83.kuznetsov.oop.model;

public class DebitAccount extends AbstractAccount implements Account{
    private String number;
    private double balance;

    public DebitAccount(String number, double balance){
        super(number, balance);
    }

     public DebitAccount()
    {
        super("", 0);
    }
}
