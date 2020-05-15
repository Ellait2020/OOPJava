package po83.kuznetsov.oop.model;
import java.time.LocalDate;

public interface Account {
    String getNumber();
    void setNumber(String number);
    double getBalance();
    void  setBalance(double balance);
    LocalDate getCreationDate();
    LocalDate getExpirationDate();
    void setExpirationDate(LocalDate ExpirationDate);
    int monthsQuantityBeforeExpiration();
    boolean isNumberNotFormatted(String number);
}
