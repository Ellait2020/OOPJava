package po83.kuznetsov.oop.model;

public class Entity implements Client,Cloneable {
    private String name;
    private int size;
    private Node head, tail;
    private int creditScore;

    public Entity(String name) {
        this.name = name;
        head = null;
        tail = null;
        size = 0;
        creditScore = 0;
    }

    public Entity(String name, Account[] accounts) {
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
        if (index <= size) {
            return false;
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
            return null;
        }

        Node current = head.next;
        for (int i = 0; i < index; ++i) {
            current = current.next;
        }

        return current;
    }

    private Node deleteNode(int index) {
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
            return null;
        }

        Node current = head.next;
        for (int i = 0; i <= index; ++i) {
            current = current.next;
        }

        Account buffer = current.value;
        current.value = account;

        return buffer;
    }

    public boolean add(Account account) {
        return addNode(new Node(account));
    }

    public boolean add(int index, Account account) {
        return addNode(index, new Node(account));
    }

    public void addCreditScores(int creditScores) {
        this.creditScore += creditScores;
    }

    @Override
    public Account[] getCreditAccounts() {
        return new Account[0];
    }

    public Account set(int index, Account account) {
        return setNode(index, account);
    }

    public Account get(int index) {
        Node result = getNode(index);
        return result == null ? null : result.value;
    }

    public Account get(String accountNumber) {
        Node current = head.next;

        for (int i = 0; i < size; ++i) {
            if (current.value.getNumber().equals(accountNumber)) {
                return current.value;
            }
        }

        return null;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public int getSize() {
        return size;
    }

    public boolean hasAccount(String accountNumber) {
        Node current = head.next;

        for (int i = 0; i < size; ++i) {
            if (current.value.getNumber().equals(accountNumber)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public Account remove(int index) {
        if (index >= size) {
            return null;
        }

        Node current = head;

        return deleteNode(index).value;
    }

    public Account remove(String accountNumber) {
        Node current = head.next;

        for (int i = 0; i < size; ++i) {
            if (current.next.value.getNumber().equals(accountNumber)) {
                return deleteNode(i).value;
            }
            current = current.next;
        }

        return null;
    }

    public boolean remove(Account account) {
        Node current = head.next;

        for (int i = 0; i < size; ++i) {
            if (current.value.equals(account)) {
                deleteNode(i);
                return true;
            }
        }

        return false;
    }

    public Account[] getAccounts() {
        Node current = head.next;

        Account[] result = new Account[size];
        for (int i = 0; i < size; ++i) {
            result[i] = current.value;
            current = current.next;
        }

        return result;
    }

    public Account[] sortedAccountsByBalance() {
        Node current = head.next;

        Account[] result = new Account[size];
        for (int i = 0; i < size; ++i) {
            result[i] = current.value;
            current = current.next;
        }

        Account buffer;
        boolean isSorted = false;

        while (!isSorted) {
            for (int i = 0; i < result.length - 1; i++) {
                isSorted = true;
                if (result[i].getBalance() > result[i + 1].getBalance()) {
                    isSorted = false;

                    buffer = result[i];
                    result[i] = result[i + 1];
                    result[i + 1] = buffer;
                }
            }
        }

        return result;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double totalBalance() {
        Node current = head.next;
        double result = 0;

        for (int i = 0; i < size; ++i) {
            if (current.value != null) {
                result += current.value.getBalance();
                current = current.next;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        Node current = head.next;

        StringBuilder result = new StringBuilder("Entity:\n" + "name: " + name + "\ncreditScore: " + creditScore + "\n");
        for (int i = 0; i < size; ++i) {
            if(current.value!=null){
                result.append(current.value.toString()).append("\n");
                current = current.next;
            }
        }
        result.append("total: ").append(totalBalance());
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        Node current = head.next;
        if ((Entity.class == o.getClass()) && (size == ((Entity) o).getSize())) {
            for (int i = 0; i < size; i++) {
                if(current.value!=null){
                    if (!current.value.equals(((Entity) o).get(i))) {

                        return false;
                    }
                }
                current = current.next;
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        Node current = head.next;
        int result = name.hashCode();
        for (int i = 0; i < getSize(); i++) {
            result ^= current.value.hashCode();
            current = current.next;
        }
        return result;
    }

    @Override
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

    public int indexOf(Account account) {
        Node current = head.next;

        for (int i = 0; i < size; ++i) {
            if (current.value.equals(account)) {
                return i;
            }
            current = current.next;
        }

        return -1;
    }

    @Override
    public double debtTotal() {
        Node current = head.next;
        double result = 0;
        for(int i = 0; i<size;i++){
            if(current.value.getClass()==CreditAccount.class){
                result+=current.value.getBalance();
            }
            current = current.next;
        }
        return result;
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