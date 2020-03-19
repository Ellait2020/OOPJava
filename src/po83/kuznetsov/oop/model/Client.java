package po83.kuznetsov.oop.model;

public interface Client {
    boolean add(Account account);
    int getSize();
    boolean add(int index, Account account);
    Account get(int index);
    Account get(String accountNumber);
    boolean hasAccount(String accountNumber);
    Account set(int index, Account account);
    Account remove(int index);
    Account remove(String accountNumber);
    Account[] getAccounts();
    Account[] sortedAccountsByBalance();
    double totalBalance();
    String getName();
    void setName(String name);
}
