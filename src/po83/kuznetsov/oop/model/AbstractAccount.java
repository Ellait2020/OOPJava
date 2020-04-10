package po83.kuznetsov.oop.model;

import java.util.Objects;

public class AbstractAccount implements Account, Cloneable {
    private String number;
    private double balance;

    protected AbstractAccount(String number, double balance) {
        this.number =number;
        this.balance =balance;
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

    @Override
    public String toString() {
        return "AbstractAccount" +
                "number:" + number + " balance:" + balance;
    }

    @Override
    public int hashCode() {
        return number.hashCode() & String.format("%f", balance).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        boolean result = o.getClass() == this.getClass();
        if (result){
            result=this.getNumber().equals(((AbstractAccount)o).getNumber())&&this.getBalance()==((AbstractAccount)o).getBalance();
        }
        return  result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

