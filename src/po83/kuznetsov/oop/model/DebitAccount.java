package po83.kuznetsov.oop.model;

public class DebitAccount  implements Account{
    private String number;
    private double balance;

    public DebitAccount(String number, double balance)
    {
        this.number = number;
        this.balance = balance;
    }

     public DebitAccount()
    {
        this.number ="";
        this.balance = 0;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }


    public void setBalance(double balance) {
        this.balance = balance;
    }
}
