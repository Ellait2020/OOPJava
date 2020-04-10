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
    int getCreditScore();
    void addCreditScores(int creditScores);
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
    Account[] getCreditAccounts();
    boolean remove(Account account);
    int indexOf(Account account);
    double debtTotal();
}
