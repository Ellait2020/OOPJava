package po83.kuznetsov.oop.model;

public class AbstractAccount implements Account {
    private String number;
    private  double balance;
    protected  AbstractAccount(String number,double balance){
        this.number="";
        this.balance=0;
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

