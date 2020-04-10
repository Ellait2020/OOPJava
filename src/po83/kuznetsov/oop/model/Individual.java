package po83.kuznetsov.oop.model;

public class Individual implements  Client,Cloneable {
    protected Account[] accounts;
    public int size;
    String name;
    private int creditScore;

    public Individual(String name) {
        this.name =name;
        size = 16;
        accounts = new Account[size];
        creditScore = 0;
    }

    public Individual(String name,int size) {
        this.name = name;
        this.size = size;
        accounts = new Account[size];
    }

    public Individual(String name,Account[] accounts) {
        this.name=name;
        size = accounts.length;
        this.accounts = new Account[size];
        for (int i = 0; i < size; i++) {
            this.accounts[i] = new DebitAccount(accounts[i].getNumber(), accounts[i].getBalance());
        }
    }

    public int getSize() {
        return size;
    }

    public boolean add(Account account) {
        for (int i = 0; i < size; i++) {
            if (accounts[i] == null) {
                accounts[i] = account;
                return true;
            }
        }


        Account[] newAccounts = new Account[size * 2];
        for (int i = 0; i < size * 2; i++) {
            if (i < accounts.length) {
                newAccounts[i] = account;
            } else {
                if (newAccounts[i] == null) {
                    newAccounts[i] = account;
                    accounts = newAccounts;
                    size *= 2;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean add(int index, Account account) {
        if (index < size && accounts[index] == null) {
            this.accounts[index] = account;
            return true;
        }
        return false;
    }

    public Account get(int index) {
        if (index < size && accounts[index] != null) {
            return accounts[index];
        }
        return null;
    }

    public Account get(String accountNumber) {
        for (int i = 0; i < size; ++i) {
            if (accounts[i].getNumber().equals(accountNumber)) {
                return accounts[i];
            }
        }
        return null;
    }

    public boolean hasAccount(String accountNumber) {
        for (int i = 0; i < size; i++) {
            if (accounts[i].getNumber().equals(accountNumber)) {
                return true;
            }
        }
        return false;
    }

    public Account set(int index, Account account) {
        Account changedAccount = accounts[index];
        accounts[index] = account;
        return changedAccount;
    }

    public Account remove(int index) {
        Account changedAccount = accounts[index];
        accounts[index] = null;

        if (size - index + 1 >= 0) System.arraycopy(accounts, index + 1, accounts, index + 1 - 1, size - index + 1);

        return changedAccount;
    }

    public Account remove(String accountNumber) {
        Account changedAccount = null;
        for (int i = 0; i < size; ++i) {
            if (accounts[i] != null) {
                if (accounts[i].getNumber().equals(accountNumber)) {
                    changedAccount = accounts[i];
                    accounts[i] = null;
                }
            }

            if (changedAccount != null && i < size - 1) {
                accounts[i] = accounts[i + 1];
            }
        }
        return changedAccount;
    }

    public Account[] getAccounts() {
        Account[] result = new Account[size];

        for (int i = 0; i < size; ++i) {
            if (accounts[i] != null) {
                result[i] = accounts[i];
            } else {
                result[i] = new DebitAccount();
            }
        }

        return result;
    }

    public Account[] sortedAccountsByBalance() {
        Account[] result = new Account[size];

        int j = 0;
        for (int i = 0; i < size; ++i) {
            if (accounts[i] != null) {
                result[j] = accounts[i];
                j++;
            }
        }

        Account buffer;
        boolean isSorted = false;

        while (!isSorted) {
            for (int i = 0; i < size - 1; i++) {
                if (result[i] != null && result[i + 1] != null) {
                    isSorted = true;
                    if (result[i].getBalance() > result[i + 1].getBalance()) {
                        isSorted = false;

                        buffer = result[i];
                        result[i] = result[i + 1];
                        result[i + 1] = buffer;
                    }
                }
            }
        }

        for (int i = 0; i < size; ++i) {
            if (result[i] == null) {
                result[i] = new DebitAccount();
            }
        }

        return result;
    }

    public double totalBalance() {
        Account[] buffer = getAccounts();
        double result = 0;

        for (Account account : buffer) {
            result += account.getBalance();
        }

        return result;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getCreditScore() {
        return creditScore;
    }

    @Override
    public void addCreditScores(int creditScore) {
        this.creditScore += creditScore;
    }

    @Override
    public Account[] getCreditAccounts() {
        return accounts;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Individual -" + " name: " + getName() + " creditScore: " + getCreditScore() + "\n");
        for (int i = 0; i < getSize(); i++) {
            if(accounts[i]!=null){
                result.append(accounts[i].toString()).append("\n");
            }
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if(o!=null){
            if ((getClass() == o.getClass()) && (size == ((Individual) o).getSize())) {
                for (int i = 0; i < size; i++) {
                    if(accounts[i]!=null){
                        if (!accounts[i].equals(((Individual) o).accounts[i])) {
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result =getName().hashCode();
        for (int i = 0; i < getSize(); i++) {
            if(accounts[i]!=null){
                result ^= accounts[i].hashCode();
            }
        }
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object result = super.clone();
        Account[] resultAccounts = new Account[size];
        for (int i = 0; i < getSize(); i++) {
            if (accounts[i].getClass() == DebitAccount.class) {
                resultAccounts[i] = new DebitAccount(accounts[i].getNumber(), accounts[i].getBalance());
            } else if (accounts[i].getClass() == CreditAccount.class) {
                resultAccounts[i] = new CreditAccount(accounts[i].getNumber(), accounts[i].getBalance(),
                        ((CreditAccount) accounts[i]).getAnnualPercentageRate());
            }
        }

        ((Individual) result).accounts = resultAccounts;

        return result;
    }

    @Override
    public boolean remove(Account account) {
        boolean result = false;
        for (int i = 0; i < size; i++) {
            if (accounts[i].equals(account)) {
                remove(i);
                result = true;
            }
        }
        return result;
    }

    @Override
    public int indexOf(Account account) {
        for (int i = 0; i < size; i++) {
            if (accounts[i].equals(account)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public double debtTotal() {
        double result = 0;
        Account[] bufferAccounts = getCreditAccounts();
        for (Account bufferAccount : bufferAccounts) {
            result += bufferAccount.getBalance();
        }
        return result;
    }
}
