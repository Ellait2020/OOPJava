package po83.kuznetsov.oop.model;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public abstract class AbstractAccount implements Account {
    private String number;
    private double balance;
    private LocalDate creationDate;
    private LocalDate expirationDate;

    protected AbstractAccount(String number, LocalDate expirationDate)
            throws NullPointerException {
        Objects.requireNonNull(number, "Номер пуст");
        Objects.requireNonNull(expirationDate, "Нет времени выпланы");
        if (isNumberNotFormatted(number)) {
            throw new InvalidAccountNumberException("Неверный формат номера");
        }
        this.number = number;
        this.creationDate = LocalDate.now();
        this.expirationDate = expirationDate;
    }

    protected AbstractAccount(String number, double balance, LocalDate creationDate, LocalDate expirationDate)
            throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(number, "Номер пуст");
        Objects.requireNonNull(creationDate, "Нет времени создания");
        Objects.requireNonNull(expirationDate, "Нет времени выплаты");
        if (creationDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Время создания в будущем");
        } else if (creationDate.isAfter(expirationDate)) {
            throw new IllegalArgumentException("Время окончания срока обслуживания назначена  раньше,чем время создания");
        }

        if (isNumberNotFormatted(number)) {
            throw new InvalidAccountNumberException("Неправильный формат номера счёта");
        }
        this.number = number;
        this.balance = balance;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        Objects.requireNonNull(number, "Номер пуст");
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        Objects.requireNonNull(expirationDate, "Нет времени выплаты");
        this.expirationDate = expirationDate;
    }

    public int monthsQuantityBeforeExpiration() {
        Period period = Period.between(
                LocalDate.now().getDayOfMonth() < 25 ? LocalDate.now() : LocalDate.now().plusMonths(1),
                expirationDate.getDayOfMonth() < 25 ? expirationDate : expirationDate.plusMonths(1));
        int result = period.getYears() * 12 + period.getMonths();
        return result == 0 ? 1 : result;
    }

    @Override
    public int compareTo(Account o) {
        return Double.compare(getBalance(), o.getBalance());
    }
}

