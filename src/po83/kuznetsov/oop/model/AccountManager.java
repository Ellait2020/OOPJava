package po83.kuznetsov.oop.model;

public class AccountManager {
    private Client[] clients;
    int size;

    public AccountManager() {

        size = 16;
        clients = new Client[size];
    }

    public AccountManager(int size) {
        clients = new Client[size];
        this.size = size;
    }

    public AccountManager(Client[] clients) {
        size = clients.length;
        this.clients = clients;
    }


    public boolean add(Client client) {
        for (int i = 0; i < size; ++i) {
            if (clients[i] == null) {
                clients[i] = client;
                return true;
            }
        }
        return false;
    }

    public boolean add(int index, Client client) {
        if (clients[index] == null) {
            clients[index] = client;
            return true;
        } else {
            return false;
        }
    }

    public Client get(int index) {
        return clients[index];
    }

    public Client set(int index, Client account) {
        Client changedAccount = clients[index];
        clients[index] = account;
        return changedAccount;
    }

    public Client remove(int index) {
        Client changedAccount = clients[index];
        clients[index] = null;
        return changedAccount;
    }

    int getSize() {
        return size;
    }

    public Client[] getClients() {
        int resSize = 0;

        for (int i = 0; i < size; ++i) {
            if (clients[i] != null) {
                resSize++;
            }
        }

        Client[] result = new Client[resSize];
        int j = 0;

        for (int i = 0; i < size; ++i) {
            if (clients[i] != null) {
                result[j++] = new Individual(clients[i].getAccounts());
            }
        }

        return result;
    }

    public Client[] sortedClientByBalance() {
        Client[] result = getClients();

        if (result.length == 0) {
            return null;
        }

        Client buffer;
        boolean isSorted = false;

        while (!isSorted) {
            for (int i = 0; i < result.length - 1; i++) {
                isSorted = true;
                if (result[i].totalBalance() > result[i + 1].totalBalance()) {
                    isSorted = false;

                    buffer = result[i];
                    result[i] = result[i + 1];
                    result[i + 1] = buffer;
                }
            }
        }

        return result;
    }

    public Account getAccount(String accountNumber) {
        Account[] buffer;

        for (int i = 0; i < size; ++i) {
            buffer = clients[i].getAccounts();
            for (int j = 0; j < buffer.length; ++j) {
                if (buffer[i].getNumber().equals(accountNumber)) {
                    return buffer[i];
                }
            }
        }

        return null;
    }

    public Account removeAccount(String accountNumber) {
        Account[] buffer;

        for (int i = 0; i < size; ++i) {
            buffer = clients[i].getAccounts();
            for (int j = 0; j < buffer.length; ++j) {
                if (buffer[i].getNumber().equals(accountNumber)) {
                    Account result = buffer[i];
                    buffer[i] = null;
                    return result;
                }
            }
        }

        return null;
    }

    public Account setAccount(String accountNumber, Account account) {
        Account result;

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < clients[i].getSize(); ++j) {
                if (clients[i].getAccounts()[j].getNumber().equals(accountNumber)) {
                    result = clients[i].getAccounts()[j];
                    clients[i].getAccounts()[j] = account;
                    return result;
                }
            }
        }

        return null;
    }
}

