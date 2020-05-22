package po83.kuznetsov.oop.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.regex.Pattern;

public class Entity implements Client {
    private static int DEF_SIZE = 0;
    private static int DEF_CREDIT_SCORE = 0;
    private String name;
    private int size;
    private Node head, tail;
    private int creditScore;

    public Entity(String name) {
        Objects.requireNonNull(name, "Имя пустое");
        this.name = name;
        head = null;
        tail = null;
        size = DEF_SIZE;
        creditScore = DEF_CREDIT_SCORE;
    }

    public Entity(String name, Account[] accounts) {
        Objects.requireNonNull(name, "Имя пустое");
        Objects.requireNonNull(accounts, "Массив аккаунтов пустой");
        this.name = name;
        size = accounts.length;

        head = new Node();

        Node current = head;
        for (Account account : accounts) {
            current.next = new Node(account);
            current = current.next;
        }

        tail = current;
        tail.next = head;

        creditScore = 0;
    }

    private boolean addNode(Node node) {
        if (head == null) {
            head = tail = new Node();
        }

        tail.next = node;
        tail = tail.next;
        tail.next = head;
        size++;

        return true;
    }

    private boolean addNode(int index, Node node) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс больше чем размер массива");
        } else if (index >= size) {
            throw new IndexOutOfBoundsException("Индекс меньше нуля");
        }

        if (head == null) {
            head = tail = new Node();
            tail.next = node;
            tail = tail.next;
            tail.next = head;
        }

        Node current = head.next;
        for (int i = 0; i < index; ++i) {
            current = current.next;
        }

        node.next = current.next;
        current.next = node;

        size++;

        return true;
    }

    private Node getNode(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Индекс больше чем размер массива");
        } else if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс меньше нуля");
        }

        Node current = head.next;
        for (int i = 0; i < index; ++i) {
            current = current.next;
        }

        return current;
    }

    private Node deleteNode(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Иднекс больше чем размер массива");
        } else if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс меньше нуля");
        }
        Node current = head;
        for (int i = 0; i < index; ++i) {
            current = current.next;
        }

        Node buffer = current.next;
        current.next = buffer.next;
        size--;

        return buffer;
    }

    private Account setNode(int index, Account account) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Иднекс больше чем размер массива");
        } else if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс меньше нуля");
        }
        Objects.requireNonNull(account, "Аккаунт пустой");
        Node current = head.next;
        for (int i = 0; i <= index; ++i) {
            current = current.next;
        }

        Account buffer = current.value;
        current.value = account;

        return buffer;
    }

    public boolean add(Account account) throws DuplicateAccountNumberException {
        Objects.requireNonNull(account, "Аккаунт пустой");

        if (isNumberMatchFound(account.getNumber())) {
            throw new DuplicateAccountNumberException("Аккаунт с номером " + account.getNumber() + " уже существует");
        }

        return addNode(new Node(account));
    }

    public boolean add(int index, Account account) throws DuplicateAccountNumberException {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Индекс больше чем размер массива");
        } else if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс меньше нуля");
        }

        Objects.requireNonNull(account, "Аккаунт пустой");

        if (isNumberMatchFound(account.getNumber())) {
            throw new DuplicateAccountNumberException("Аккаунт с номером " + account.getNumber() + " уже существует");
        }
        return addNode(index, new Node(account));
    }

    public void addCreditScores(int creditScores) {
        this.creditScore += creditScores;
    }

    public Account get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Индекс больше чем размер массива");
        } else if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс меньше нуля");
        }
        Node result = getNode(index);
        return result == null ? null : result.value;
    }

    @Override
    public Account set(int index, Account account) throws DuplicateAccountNumberException {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Индекс больше,чем размер массива");
        } else if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс больше нуля");
        }

        Objects.requireNonNull(account, "Аккаунт пустой");
        Node current=head.next;
        Account changedAccount=null;
        for (int i = 0; i < size; ++i) {
            if (current.value != null) {
                if (current.value.getNumber().equals(account.getNumber())) {
                    throw new DuplicateAccountNumberException("Номер аккаунта " + account.getNumber() + " уже существует");
                }
            }
            if(i==index){
                changedAccount=current.value;
                current.value=account;
            }
            current=current.next;
        }
        return changedAccount;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public int indexOf(Account account) {
        Objects.requireNonNull(account, "Аккаунт пустой");
        Node current = head.next;

        for (int i = 0; i < size; ++i) {
            if (current.value.equals(account)) {
                return i;
            }
            current = current.next;
        }

        return -1;
    }

    public int getSize() {
        return size;
    }

    public Account remove(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Индекс больше чем размер массива");
        } else if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс меньше нуля");
        }

        Node current = head;

        return deleteNode(index).value;
    }

    public Account remove(String accountNumber) {
        Objects.requireNonNull(accountNumber, "Номер аккаунта пустой");

        if (isNumberNotFormatted(accountNumber)) {
            throw new InvalidAccountNumberException("Неверный формат номера аккаунта");
        }

        Account result = null;

        Node current = head.next;

        for (int i = 0; i < size; ++i) {
            if (current.next.value.getNumber().equals(accountNumber)) {
                result = deleteNode(i).value;
                break;
            }
            current = current.next;
        }
        if (Objects.isNull(result)) {
            throw new NoSuchElementException("account with number " + accountNumber + " not found");
        }

        return result;
    }

    public boolean remove(Account account) {
        Objects.requireNonNull(account, "Аккаунт пустой");
        Node current = head.next;

        for (int i = 0; i < size; ++i) {
            if (current.value.equals(account)) {
                deleteNode(i);
                return true;
            }
        }

        return false;
    }

    public void setName(String name) {
        Objects.requireNonNull(name, "Имя пустое");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private boolean isNumberMatchFound(String accountNumber) {
        if (head == null) {
            head = tail = new Node();
            head.next = tail;
        }

        Node current = head.next;
        for (int i = 0; i < size; ++i) {
            if (current.value.getNumber().equals(accountNumber)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean isNumberNotFormatted(String accountNumber) {
        Objects.requireNonNull(accountNumber, "Номер аккаунта пустой");
        return !Pattern.matches("^4[045]\\d{3}810\\d(?!0{4})\\d{4}(?!0{7})\\d{7}$", accountNumber);
    }

    public String toString() {
        Node current;

        StringBuilder result = new StringBuilder("Entity:\n" + "имя: " + name +
                "\ncreditScore: " + creditScore + "\n");

        if (size != 0) {
            current = head.next;
            for (int i = 0; i < size; ++i) {
                result.append(current.value.toString()).append("\n");
                current = current.next;
            }
        }
        result.append("total: ").append(totalBalance());
        return result.toString();
    }

    public int hashCode() {
        Node current;
        int result = name.hashCode() ^ creditScore;
        if (size != 0) {
            current = head.next;
            for (int i = 0; i < size; ++i) {
                result ^= current.value.hashCode();
                current = current.next;
            }
        }
        return result;
    }

    public boolean equals(Object o) {
        Node current;
        if ((this.getClass() == o.getClass()) && name.equals(((Entity) o).getName()) && (size == ((Entity) o).getSize())) {
            if (size != 0) {
                current = head.next;
                for (int i = 0; i < size; i++) {
                    if (!current.value.equals(((Entity) o).get(i))) {
                        return false;
                    }
                    current = current.next;
                }
            }
            return true;
        }
        return false;
    }

    protected Object clone() throws CloneNotSupportedException {
        Object result = super.clone();

        ((Entity) result).head = ((Entity) result).tail = new Node();
        ((Entity) result).head.next = ((Entity) result).tail;
        ((Entity) result).tail.next = ((Entity) result).head;
        ((Entity) result).size = 0;

        Account[] accounts = getAccounts();

        for (int i = 0; i < ((Entity) result).getSize(); ++i) {
            ((Entity) result).addNode(new Node(accounts[i]));
        }
        return result;
    }

    @Override
    public Iterator<Account> iterator() {
        return new Entity.AccountIterator();
    }

    public int compareTo(Client o) {
        return Double.compare(totalBalance(), o.totalBalance());
    }

    private class AccountIterator implements java.util.Iterator<Account> {
        private int index = 0;
        private Node node=head.next;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Account next() {
            if (hasNext()) {
                index++;
                if (node != null) {
                    Account result;
                    result = node.value;
                    node = node.next;
                    return result;
                } else {
                    return null;}
                } else{
                    throw new NoSuchElementException("Элеменет не найден");
                }
            }
        }
    }
    class Node {
        Account value;
        Node next;

        public Node() {
        }

        public Node(Account value) {
            this.value = value;
        }
    }