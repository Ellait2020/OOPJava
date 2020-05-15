package po83.kuznetsov.oop.model;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.regex.Pattern;

public class Individual implements  Client{
    private static int DEF_SIZE = 16;
    private static int DEF_CREDIT_SCORE = 0;
    protected Account[] accounts;
    public int size;
    String name;
    private int creditScore;

    public Individual(String name) {
        Objects.requireNonNull(name, "Название пустое");
        accounts = new Account[DEF_SIZE];
        size = DEF_SIZE;
        this.name = name;
        creditScore = DEF_CREDIT_SCORE;
    }

    public Individual(String name, int size) {
        Objects.requireNonNull(name, "Название пустое");
        accounts = new Account[size];
        this.size = size;
        this.name = name;
        creditScore = DEF_SIZE;
    }

        public Individual(String name, Account[] accounts) {
            Objects.requireNonNull(name, "Название пустое");
            Objects.requireNonNull(accounts, "Массив аккаунтов пустой");

            size = accounts.length;
        this.accounts = new Account[size];
        for (int i = 0; i < size; i++) {
            this.accounts[i] = new DebitAccount(
                    accounts[i].getNumber(), accounts[i].getBalance(),
                    accounts[i].getCreationDate(), accounts[i].getExpirationDate());
        }
            this.name = name;
            creditScore = 0;
    }

    public int getSize() {
        return size;
    }
    public boolean add(Account account) throws DuplicateAccountNumberException {
        Objects.requireNonNull(account, "Аккаунт пустой");

        if (isNumberMatchFound(account.getNumber())) {
            throw new DuplicateAccountNumberException("Номер аккаунта " + account.getNumber() + " уже существует");
        }
        for (int i = 0; i < size; i++) {
            if (accounts[i] == null) {
                accounts[i] = account;
                return true;
            }
        }
        doubleAccountsArraySize();
        return  add(account);
        }

    private void doubleAccountsArraySize()
    {
        Account[] newAccounts = new Account[size * 2];

        System.arraycopy(accounts, 0, newAccounts, 0, size);
        size *= 2;

        accounts = newAccounts;
    }

    private boolean isNumberMatchFound(String accountNumber) {
        for (int i = 0; i < size; ++i) {
            if (accounts[i] != null) {
                if (accounts[i].getNumber().equals(accountNumber)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isNumberNotFormatted(String accountNumber) {
        Objects.requireNonNull(accountNumber, "accountNumber is null");
        return !Pattern.matches("^4[045]\\d{3}810\\d(?!0{4})\\d{4}(?!0{7})\\d{7}$", accountNumber);
    }

    public boolean add(int index, Account account) throws DuplicateAccountNumberException {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Индекс больше,чем размер массива");
        } else if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс меньше нуля");
        }

        Objects.requireNonNull(account, "Аккаунт пустой");

        if (isNumberMatchFound(account.getNumber())) {
            throw new DuplicateAccountNumberException("Номер аккаунта " + account.getNumber() + " уже сущеуствует");
        }
        if (index < size && accounts[index] == null) {
            this.accounts[index] = account;
            return true;
        }
        return false;
    }

    public Account get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Индекс больше,чем размер массива");
        } else if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс меньше нуля");
        }

        return accounts[index];
    }

    public Account get(String accountNumber){
        Objects.requireNonNull(accountNumber, "Номер аккаунта пуст");

        if (isNumberNotFormatted(accountNumber)) {
            throw new InvalidAccountNumberException("Неверный формат номера аккаунта");
        }

        Account result = null;
        for(int i=0;i<size;++i){
            if (accounts[i] != null) {
                if (accounts[i].getNumber().equals(accountNumber)) {
                    result = accounts[i];
                    break;
                }
            }
        }
        if (Objects.isNull(result)) {
            throw new NoSuchElementException("Аккаут с номером " + accountNumber + " не найден");
        }

        return result;
    }

    public int indexOf(Account account) {
        Objects.requireNonNull(account, "Аккаунт пустой");

        for (int i = 0; i < size; ++i) {
                if (accounts[i] != null) {
                    if (accounts[i].equals(account)) {
                        return i;
                    }
                }
            }

            return -1;
        }

    public boolean hasAccount(String accountNumber) {
        Objects.requireNonNull(accountNumber, "Номер аккаунта пустой");

        if (isNumberNotFormatted(accountNumber)) {
            throw new InvalidAccountNumberException("Неверный формат номера аккаунта");
        }
        for (int i = 0; i < size; i++) {
            if (accounts[i] != null) {
                if (accounts[i].getNumber().equals(accountNumber)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Account set(int index, Account account) throws DuplicateAccountNumberException {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Индекс больше,чем размер массива");
        } else if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс больше нуля");
        }

        Objects.requireNonNull(account, "Аккаунт пустой");

        for (int i = 0; i < size; ++i) {
            if (accounts[i] != null) {
                if (accounts[i].getNumber().equals(account.getNumber())) {
                    throw new DuplicateAccountNumberException("Номер аккаунта " + account.getNumber() + " уже существует");
                }
            }
        }

        Account changedAccount = accounts[index];
        accounts[index] = account;
        return changedAccount;
    }


    public Account remove(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Индекс больше,чем размер массива");
        } else if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс больше нуля");
        }
        Account changedAccount = accounts[index];
        accounts[index] = null;
        if (size - index + 1 >= 0) {
            System.arraycopy(accounts, index + 1, accounts, index, size - index + 1);
        }

        return changedAccount;
    }

    public Account remove(String accountNumber) {
        Objects.requireNonNull(accountNumber, "Номер аккаунта пустой");

        if (isNumberNotFormatted(accountNumber)) {
            throw new InvalidAccountNumberException("Неверный формат номера аккаунта");
        }
        for (int i = 0; i < size; ++i) {
            if (accounts[i] != null) {
                if (accounts[i].getNumber().equals(accountNumber)) {
                    return remove(i);
                }
            }
        }
            throw new NoSuchElementException("аккаунт с номером " + accountNumber + " не найден");
    }

    public boolean remove(Account account) {
        Objects.requireNonNull(account, "аккаунт не пустой");

        for (int i = 0; i < size; ++i) {
                if (accounts[i] != null) {
                    if (accounts[i].equals(account)) {
                        remove(i);
                        return true;
                    }
                }
            }
            return false;
        }

    public Account[] getAccounts() {
        Account[] result = new Account[size];
        System.arraycopy(accounts, 0, result, 0, size);
        return result;
    }

    public Account[] sortedAccountsByBalance() {
        Account[] result = new Account[size];
        System.arraycopy(accounts, 0, result, 0, size);
        Account buffer;
        boolean isSorted = false;

        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < size-1; i++) {
                if (result[i + 1] != null) {
                    if (result[i] == null || result[i].getBalance() > result[i + 1].getBalance()) {
                        isSorted = false;

                        buffer = result[i];
                        result[i] = result[i + 1];
                        result[i + 1] = buffer;
                    }
                }
            }
        }
        return result;
    }

    public double debtTotal() {
        double result = 0;

        for (int i = 0; i < size; ++i) {
                if (accounts[i] != null) {
                    if (accounts[i].getClass() == CreditAccount.class) {
                        result += accounts[i].getBalance();
                    }
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
        return  name;
    }

    @Override
    public void setName(String name) {
        Objects.requireNonNull(name, "Имя пустое");
        this.name = name;
    }

    @Override
    public int getCreditScore() {
        return creditScore;
    }

    @Override
    public void addCreditScores(int creditScores) {
        this.creditScore += creditScores;
    }

    @Override
    public Account[] getCreditAccounts() {
        int newSize = 0;
        for (int i = 0; i < size; ++i) {
            if (accounts[i] != null) {
                if (accounts[i].getClass() == CreditAccount.class) {
                    newSize++;
                }
            }
        }
        Account[] result = new Account[newSize];
        int j = 0;
        for (int i = 0; i < size; ++i) {
            if (accounts[i] != null) {
                if (accounts[i].getClass() == CreditAccount.class) {
                    System.arraycopy(accounts, i, result, j, 1);
                    j++;
                }
            }
        }
        return result;
    }

    public String toString() {
        StringBuilder result = new StringBuilder("Individual:\n" + "name: " + name + "\ncreditScore: " + creditScore + "\n");
        for (Account a : accounts) {
            if (a != null) {
                result.append(a.toString()).append("\n");
            }
        }
        result.append("total: ").append(totalBalance());
        return result.toString();
    }
    @Override
    public int hashCode() {
        int result = name.hashCode() ^ creditScore;

        for (Account a : accounts) {
            if (a != null) {
                result ^= a.hashCode();
            }
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
            if ((getClass() == o.getClass()) && name.equals(((Individual) o).getName()) && (size == ((Individual) o).getSize())) {
                for (int i = 0; i < size; i++) {
                        if (accounts[i] != null) {
                            if (!accounts[i].equals(((Individual) o).accounts[i])) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
                return false;
            }

            @Override
            protected Object clone() throws CloneNotSupportedException {
                Object result = super.clone();

                Account[] resultAccounts = new Account[size];

                for (int i = 0; i < size; ++i) {
                    if (accounts[i].getClass() == DebitAccount.class) {
                        resultAccounts[i] = new DebitAccount(accounts[i].getNumber(), accounts[i].getBalance(),
                                accounts[i].getCreationDate(), accounts[i].getExpirationDate());
                    } else if (accounts[i].getClass() == CreditAccount.class) {
                        resultAccounts[i] = new CreditAccount(accounts[i].getNumber(), accounts[i].getBalance(),
                                accounts[i].getCreationDate(), accounts[i].getExpirationDate(),
                                ((CreditAccount) accounts[i]).getAnnualPercentageRate());
                    }
                }

                ((Individual) result).accounts = resultAccounts;

                return result;
            }
}
