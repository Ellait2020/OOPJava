package po83.kuznetsov.oop.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.regex.Pattern;

public class Individual implements  Client {
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
            if (accounts[i].getClass() == DebitAccount.class) {
                this.accounts[i] = new DebitAccount(
                        accounts[i].getNumber(), accounts[i].getBalance(),
                        accounts[i].getCreationDate(), accounts[i].getExpirationDate());
            } else if (accounts[i].getClass() == CreditAccount.class) {
                this.accounts[i] = new CreditAccount(
                        accounts[i].getNumber(), accounts[i].getBalance(),
                        accounts[i].getCreationDate(), accounts[i].getExpirationDate(),
                        ((CreditAccount) accounts[i]).getAnnualPercentageRate());
            }
        }
        this.name = name;
        creditScore = 0;
    }

    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        for (Account bufer : this) {
            if (!Objects.isNull(bufer)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean contains(Object o) {
        for (Account bufer : this) {
            if(!Objects.isNull(bufer)){
                if (bufer.equals(o)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        Objects.requireNonNull(o, "Передан пустой объект");
        for (int i = 0; i < size(); ++i)
        {
            if (!Objects.isNull(accounts[i])) {
                if (accounts[i].equals(o)) {
                    System.arraycopy(accounts, i + 1, accounts, i, size() - i - 1);
                    accounts[size() - i - 1] = null;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Objects.requireNonNull(c, "Передана пустая коллекция");
        boolean result = false;
        for (Object o : c) {
            for (Account account : this) {
                if (Objects.isNull(account)) {
                    if (Objects.isNull(o)) {
                        result = true;
                    }
                } else if (account.equals(o)) {
                    result = true;
                    break;
                }
            }
            if (!result) {
                break;
            }
        }
        return result;
    }

    @Override
    public boolean addAll(Collection<? extends Account> c) {
        Objects.requireNonNull(c, "Передана пустая коллекция");
        for (Account account : c) {
            for (int i = 0; i < size; i++) {
                if (Objects.isNull(accounts[i])) {
                    accounts[i] = account;
                    break;
                }
                if (i == size - 1) {
                    Account[] newAccounts = new Account[size * 2];
                    System.arraycopy(accounts, 0, newAccounts, 0, size);
                    accounts = newAccounts;
                    size *= 2;
                }
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c, "Передана пустая коллекция");

        int index;
        boolean result = false;
        for (Object o : c) {
            index = 0;
            for (int i = 0; i < size; i++) {
                if (Objects.isNull(accounts[i])) {
                    if (Objects.isNull(o)) {
                        result = true;
                        break;
                    }
                } else if (accounts[i].equals(o)) {
                    System.arraycopy(accounts, i + 1, accounts, i, size() - i - 1);
                    accounts[size() - i - 1] = null;
                    result = true;
                    break;
                }
                index++;
            }
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c, "Передана пустая коллекция");

        boolean result = false;

        int index = 0;
        boolean isEquals;
        boolean isChanged;
        for (Account account : this) {
            isEquals = false;
            isChanged = false;
            for (Object o : c) {
                if (Objects.isNull(account)) {
                    if (Objects.isNull(o)) {
                        isEquals = true;
                        break;
                    }
                } else if (account.equals(o)) {
                    isEquals = true;
                    break;
                } else {
                    isChanged = true;
                }
            }

            if (isEquals) {
                index++;
            } else if (isChanged) {
                System.arraycopy(accounts, index + 1, accounts, index, size() - index - 1);
                accounts[size() - index - 1] = null;
                result = true;
            }
        }
        return result;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; ++i) {
            accounts[i] = null;
        }
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

    @Override
    public boolean add(Account account) {
        Objects.requireNonNull(account, "Аккаунт пустой");

        int index = 0;
        for (Account buffer : this) {
            if (Objects.isNull(buffer)) {
                accounts[index] = account;
                return true;
            }
            index++;
        }
        doubleAccountsArraySize();
        return add(account);
    }

    private void doubleAccountsArraySize() {
        Account[] newAccounts = new Account[size * 2];

        System.arraycopy(accounts, 0, newAccounts, 0, size);
        size = newAccounts.length;

        accounts = newAccounts;
    }

    public Account get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Индекс больше,чем размер массива");
        } else if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс меньше нуля");
        }

        return accounts[index];
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

    @Override
    public String getName() {
        return name;
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

    public void addCreditScores(int creditScores) {
        this.creditScore += creditScores;
    }

    public String toString() {
        StringBuilder result = new StringBuilder("Individual:\n" + "Имя: " + name + "\ncreditScore: " + creditScore + "\n");
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
        if ((getClass() == o.getClass()) && name.equals(((Individual) o).getName()) && (size == ((Individual) o).size())) {
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

    @Override
    public Iterator<Account> iterator() {
        return new AccountIterator();
    }

    @Override
    public Object[] toArray() {
        Account[] result = new Account[size];
        int index = 0;
        for (Account buffer : this) {
            result[index] = buffer;
            index++;
        }
        return result;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        Account[] result = new Account[size];
        int index = 0;
        for (Account account : this) {
            result[index] = account;
            index++;
        }
        return (T[]) result;
    }

    public int compareTo(Client o) {
        return Double.compare(totalBalance(), o.totalBalance());
    }

    private class AccountIterator implements java.util.Iterator<Account> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Account next() {
            if (hasNext()) {
                index++;
                return accounts[index - 1];
            } else {
                throw new NoSuchElementException("Элменет не найден");
            }
        }
    }
}
