package po83.kuznetsov.oop.model;

import java.util.*;
import java.util.regex.Pattern;

public interface Client extends java.lang.Iterable<Account>,java.lang.Comparable<Client>,java.util.Collection<Account> {
    int size();
    boolean add(int index, Account account) throws DuplicateAccountNumberException;
    Account get(int index);
    default public Account get(String accountNumber) {
        Objects.requireNonNull(accountNumber, "Номер аккаунта пуст");

        if (isNumberNotFormatted(accountNumber)) {
            throw new InvalidAccountNumberException("Неверный формат номера аккаунта");
        }

        Account result = null;
        for(Account buffer:this){
            if (buffer != null) {
                if (buffer.getNumber().equals(accountNumber)) {
                    result = buffer;
                    break;
                }
            }
        }
        if (Objects.isNull(result)) {
            throw new NoSuchElementException("Аккаут с номером " + accountNumber + " не найден");
        }

        return result;
    }
    default public boolean hasAccount(String accountNumber) {
        Objects.requireNonNull(accountNumber, "Номер аккаунта пустой");

        if (isNumberNotFormatted(accountNumber)) {
            throw new InvalidAccountNumberException("Неверный формат номера аккаунта");
        }
        for (Account buffer:this) {
            if (buffer != null) {
                if (buffer.getNumber().equals(accountNumber)) {
                    return true;
                }
            }
        }
        return false;
    }
    Account set(int index, Account account) throws DuplicateAccountNumberException;
    Account remove(int index);
    Account remove(String accountNumber);
    default List<Account> sortedAccountsByBalance() {
        List<Account> result = new ArrayList<>(this);
        Collections.sort(result);
        return result;
    }
    default public double totalBalance() {
        double result = 0;

        for (Account bufferAccount : this) {
            if (bufferAccount != null) {
                result += bufferAccount.getBalance();
            }
        }

        return result;
    }
    String getName();
    void setName(String name);
    default Collection<Credit> getCreditAccounts() {
        LinkedList<Credit> result = new LinkedList<>();

        for (Account account : this) {
            if (!Objects.isNull(account)) {
                if (account.getClass().equals(CreditAccount.class)) {
                    result.add((Credit) account);
                }
            }
        }

        return result;
    }
    public int getCreditScore();
    public void addCreditScores(int creditScores);
    default public double debtTotal() {
        double result = 0;

        for (Account buffer:this) {
            if (buffer != null) {
                if (buffer.getClass() == CreditAccount.class) {
                    result += buffer.getBalance();
                }
            }
        }
        return result;
    }
    default ClientStatus getStatus() {
        int creditScores = getCreditScore();

        if (creditScores >= 5)
        {
            return ClientStatus.PLATINUM;
        }
        else if (creditScores >= 3)
        {
            return ClientStatus.GOLD;
        }
        else if (creditScores >= 0)
        {
            return ClientStatus.GOOD;
        }
        else if (creditScores > -4)
        {
            return ClientStatus.RISKY;
        }
        else
        {
            return ClientStatus.BAD;
        }
    }

    default public boolean isNumberNotFormatted(String accountNumber) {
        Objects.requireNonNull(accountNumber, "Номер аккаунта пустой");
        return !Pattern.matches("^4[045]\\d{3}810\\d(?!0{4})\\d{4}(?!0{7})\\d{7}$", accountNumber);
    }
}
