package po83.kuznetsov.oop;

import po83.kuznetsov.oop.model.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        //lab1tests();
        //lab2tests();
        //lab3tests();
        //lab4tests();
        //lab5tests();
        //lab6tests();
        lab7tests();
    }

    static void lab7tests() {
        Account a1 = new DebitAccount("40000810100011234561", LocalDate.now().plusMonths(1));
        Account a2 = new DebitAccount("40000810100011234562", LocalDate.now().plusMonths(1));
        Account a3 = new DebitAccount("40000810100011234563", LocalDate.now().plusMonths(1));
        Account a4 = new CreditAccount("44000810100011234564", LocalDate.now().plusMonths(1));
        Account a5 = new CreditAccount("44000810100011234565", LocalDate.now().plusMonths(1));
        Account[] accounts = new Account[2];
        accounts[0] = a1;
        accounts[1] = a2;

        System.out.println("Проверка Collection в Entity");
        Entity entity1 = new Entity("Entity 1");
        System.out.println(entity1.add(a1));
        System.out.println(entity1.add(a2));
        System.out.println(entity1.addAll(Arrays.asList(accounts)));
        System.out.println(entity1.remove(a4));
        System.out.println(entity1.remove(a1));
        System.out.println(entity1.remove(a1));
        System.out.println(entity1.removeAll(Arrays.asList(accounts)));
        System.out.println(entity1.removeAll(Arrays.asList(accounts)));
        entity1.addAll(Arrays.asList(accounts));
        System.out.println(entity1.retainAll(Arrays.asList(accounts)));
        System.out.println(entity1.retainAll(Arrays.asList(accounts)));
        System.out.println(entity1.containsAll(Arrays.asList(accounts)));
        System.out.println(entity1.contains(a5));
        entity1.clear();
        entity1.add(a4);
        System.out.println(entity1.containsAll(Arrays.asList(accounts)));
        System.out.println(entity1.contains(a5));
        System.out.println(entity1.contains(a4));
        System.out.println(entity1.isEmpty());
        entity1.remove(a4);
        System.out.println(entity1.isEmpty());
        System.out.println();
        entity1.clear();
        entity1.addAll(Arrays.asList(accounts));
        for (Credit account : entity1.getCreditAccounts()) {
            System.out.println(account);
        }

        System.out.println("Проверка Collection в Individual");
        Individual individual1 = new Individual("Individual 1");
        System.out.println(individual1.add(a1));
        System.out.println(individual1.add(a2));
        System.out.println(individual1.addAll(Arrays.asList(accounts)));
        System.out.println(individual1.remove(a4));
        System.out.println(individual1.remove(a1));
        System.out.println(individual1.remove(a1));
        System.out.println(individual1.removeAll(Arrays.asList(accounts)));
        System.out.println(individual1.removeAll(Arrays.asList(accounts)));
        individual1.addAll(Arrays.asList(accounts));
        System.out.println(individual1.retainAll(Arrays.asList(accounts)));
        System.out.println(individual1.retainAll(Arrays.asList(accounts)));
        System.out.println(individual1.containsAll(Arrays.asList(accounts)));
        System.out.println(individual1.contains(a5));
        individual1.clear();
        individual1.add(a4);
        System.out.println(individual1.containsAll(Arrays.asList(accounts)));
        System.out.println(individual1.contains(a5));
        System.out.println(individual1.contains(a4));
        System.out.println(individual1.isEmpty());
        individual1.remove(a4);
        System.out.println(individual1.isEmpty());
        System.out.println();
        individual1.clear();
        individual1.addAll(Arrays.asList(accounts));
        for (Credit account : individual1.getCreditAccounts()) {
            System.out.println(account);
        }

        System.out.println("Проверка Collection в AccountManager");
        entity1.add(a4);
        individual1.add(a5);
        AccountManager am = new AccountManager();
        am.add(entity1);
        am.add(individual1);
        individual1.addCreditScores(-4);
        System.out.println(Arrays.toString(am.getClients()));
        System.out.println("\nDebtors:");
        System.out.println(Arrays.toString(am.getDebtors().toArray()));
        System.out.println("\nWickedDebtors:");
        System.out.println(Arrays.toString(am.getWickedDebtors().toArray()));
    }
}
  /*  public static void lab6tests() {
        Account[] accounts = new Account[5];
        accounts[0] = new DebitAccount("40000810100010000001", 1, LocalDate.now(),
                LocalDate.now().plusMonths(1));
        accounts[1] = new CreditAccount("44000810100010000002", -1,
                LocalDate.now(), LocalDate.now().plusMonths(1), 10);
        accounts[2] = new DebitAccount("40000810100010000003", 2, LocalDate.now(),
                LocalDate.now().plusMonths(1));
        accounts[3] = new CreditAccount("44000810100010000004", -2,
                LocalDate.now(), LocalDate.now().plusMonths(1), 10);
        accounts[4] = new DebitAccount("40000810100010000005", 3, LocalDate.now(),
                LocalDate.now().plusMonths(1));

        System.out.println("Проверка компараторов и итераторов в классах реализующих интерфейс Account:");
        Arrays.sort(accounts);
        for (Account a : accounts) {
            System.out.println(a);
        }
        Account[] accounts2 = new Account[5];
        accounts2[0] = new DebitAccount("40000810100010000001", 1, LocalDate.now(),
                LocalDate.now().plusMonths(1));
        accounts2[1] = new CreditAccount("44000810100010000002", -1,
                LocalDate.now(), LocalDate.now().plusMonths(1), 10);
        accounts2[2] = new DebitAccount("40000810100010000003", 2, LocalDate.now(),
                LocalDate.now().plusMonths(1));
        accounts2[3] = new CreditAccount("44000810100010000004", -2,
                LocalDate.now(), LocalDate.now().plusMonths(1), 10);
        accounts2[4] = new DebitAccount("40000810100010000005", 3, LocalDate.now(),
                LocalDate.now().plusMonths(1));
        System.out.println();
        System.out.println("Проверка компараторов и итераторов в классах реализующих интерфейс Client:");
        Entity e1 = new Entity("Entity1", accounts2);
        for (Account a : e1.sortedAccountsByBalance()) {
            System.out.println(a);
        }
        System.out.println();

        Account[] accounts3 = new Account[5];
        accounts3[0] = new DebitAccount("40000810100010000001", 1, LocalDate.now(),
                LocalDate.now().plusMonths(1));
        accounts3[1] = new CreditAccount("45000810100010000002", -1,
                LocalDate.now(), LocalDate.now().plusMonths(1), 10);
        accounts3[2] = new DebitAccount("40000810100010000003", 2, LocalDate.now(),
                LocalDate.now().plusMonths(1));
        accounts3[3] = new CreditAccount("44000810100010000004", -2,
                LocalDate.now(), LocalDate.now().plusMonths(1), 10);
        accounts3[4] = new DebitAccount("40000810100010000005", 3, LocalDate.now(),
                LocalDate.now().plusMonths(1));

        Individual i1 = new Individual("Individual1", accounts3);
        for (Account a : i1.sortedAccountsByBalance()) {
            System.out.println(a);
        }
        System.out.println();

        System.out.println("Проверка итераторов и компараторов вручную:");
        Iterator<Account> iterator1 = e1.iterator();
        while (iterator1.hasNext()) {
            System.out.println(iterator1.next());
        }
        System.out.println();
        for (Account account : e1) {
            System.out.println(account);
        }
        System.out.println();

        System.out.println("Проверка итераторов и компараторов интерфейса Client");
        Client[] clients1 = new Client[3];
        accounts[0].setBalance(accounts[0].getBalance() + 2);
        clients1[0] = new Entity("Client Entity 1", accounts);
        accounts2[0].setBalance(accounts2[0].getBalance() + 3);
        clients1[1] = new Individual("Client Individual 2", accounts2);
        accounts3[0].setBalance(accounts3[0].getBalance() + 1);
        clients1[2] = new Entity("Client Entity 3", accounts3);
        Arrays.sort(clients1);
        for (Client c : clients1) {
            System.out.println(c.getName() + " " + c.totalBalance());
        }
        System.out.println();

        System.out.println("Проверка изменённых методов:");
        System.out.println("Проверка клиентов у которых кредит или просроченный кредит:");
        System.out.println(e1.get("40000810100010000001"));
        System.out.println(i1.get("40000810100010000001"));
        System.out.println(e1.hasAccount("44000810100010000002"));
        System.out.println(i1.hasAccount("44000810100010000002"));
        System.out.println(e1.debtTotal());
        System.out.println(i1.debtTotal());
        {
            Account[] creditAccounts;
            creditAccounts = e1.getCreditAccounts();
            for (Account account : creditAccounts) {
                System.out.println(account);
            }
            System.out.println();
            creditAccounts = i1.getCreditAccounts();
            for (Account account : creditAccounts) {
                System.out.println(account);
            }
        }
        DebitAccount debitAccount1 = new DebitAccount("40000810100010000001", 123,
                LocalDate.now(), LocalDate.now().plusMonths(1));

        DebitAccount debitAccount2 = new DebitAccount("40000810100010000002", 456,
                LocalDate.now(), LocalDate.now().plusMonths(1));

        CreditAccount creditAccount1 = new CreditAccount("44000810100010000001", -789,
                LocalDate.now(), LocalDate.now().plusMonths(1), 10);

        CreditAccount creditAccount2 = new CreditAccount("44000810100010000002", -321,
                LocalDate.now(), LocalDate.now().plusMonths(1), 10);

        try {
            System.out.println("Проверка методов:");
            Account[] accounts1;
            Individual individual1 = new Individual("Денис");
            individual1.add(creditAccount1);
            individual1.add(new DebitAccount("40000810100010000003", 87.4,
                    LocalDate.now(), LocalDate.now().plusMonths(1)));
            individual1.add(creditAccount2);
            individual1.add(new DebitAccount("40000810100010000004", 27.908,
                    LocalDate.now(), LocalDate.now().plusMonths(1)));
            individual1.add(new DebitAccount("40000810100010000005", 93,
                    LocalDate.now(), LocalDate.now().plusMonths(1)));
            individual1.add(new CreditAccount("45000810100010000003", -456.3,
                    LocalDate.now(), LocalDate.now().plusMonths(1), 10));
            individual1.addCreditScores(-10);
            Individual i2 = new Individual("Николай", 2);
            i2.add(debitAccount1);
            i2.add(debitAccount2);
            Entity entity1 = new Entity("Михаил");
            entity1.add(new CreditAccount("45000810100010000004", -320,
                    LocalDate.now(), LocalDate.now().plusMonths(1), 10));
            entity1.add(new CreditAccount("45000810100010000005", -520,
                    LocalDate.now(), LocalDate.now().plusMonths(1), 10));
            entity1.add(new CreditAccount("45000810100010000006", -380,
                    LocalDate.now(), LocalDate.now().plusMonths(1), 10));
            entity1.addCreditScores(5);
            AccountManager am = new AccountManager();
            am.add(individual1);
            am.add(i2);
            am.add(entity1);
            System.out.println("\nВсе клиенты:");
            System.out.println(am);
            System.out.println("\nВсе аккаунты с кредитами:");
            for (Client client : am.getDebtors()) {
                System.out.println(client.getName());
            }
            System.out.println("\nВсе аккаунты с просроченным кредитом:");
            for (Client client : am.getWickedDebtors()) {
                System.out.println(client.getName());
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
  /* public static void lab5tests() {
        System.out.println("Проверка класса AbstractAccount:");
        System.out.println("Проверка всех номеров:");
        try {
            DebitAccount test = new DebitAccount("Плохой номер", LocalDate.now().plusMonths(1));
            System.out.println(test.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println("Поверка типа дебитового счета (AAAAAA):");
            //40000
            DebitAccount test = new DebitAccount("45000810100011234567", LocalDate.now().plusMonths(1));
            System.out.println(test.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println("Поверка кода валюты(CCC):");
            //810
            DebitAccount test = new DebitAccount("40000811100011234567", LocalDate.now().plusMonths(1));
            System.out.println(test.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println("Поверка порядкового номера подразделения банка (DDDD):");
            //0001-9999
            DebitAccount test = new DebitAccount("40000810100001234567", LocalDate.now().plusMonths(1));
            System.out.println(test.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println("Поверка порядкового номера счета (NNNNNNN):");
            //0000001-9999999
            DebitAccount test = new DebitAccount("40000810100010000000", LocalDate.now().plusMonths(1));
            System.out.println(test.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println("Поверка типа кредитного счета (AAAAAA):");
            // 45000 или 44000
            CreditAccount test2 = new CreditAccount("40000810100011234567", LocalDate.now().plusMonths(1));
            System.out.println(test2.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println();
            CreditAccount test3 = new CreditAccount("44000810100011234567", LocalDate.now().plusMonths(1));
            System.out.println("Правильный номер кредитного аккаунта(4400)");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            CreditAccount test3 = new CreditAccount("45000810100011234567", LocalDate.now().plusMonths(1));
            System.out.println("Правильный номер кредитного аккаунта(4500)");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            DebitAccount test3 = new DebitAccount("40000810100011234567", LocalDate.now().plusMonths(1));
            System.out.println("Правильный номер дебетового аккаунта(4000)");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\nПроверка передачи баланса");
        try {
            DebitAccount test = new DebitAccount("40000810100011234567",
                    -1, LocalDate.now(), LocalDate.now().plusMonths(1));
            System.out.println("Провера отрицательного баланса в дебетовом счёте:");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            CreditAccount test2 = new CreditAccount("45000810100011234567",
                    1234, LocalDate.now(), LocalDate.now().plusMonths(1), 5);
            System.out.println("Провера положительного баланса в кредитном счёте:");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            DebitAccount test = new DebitAccount("40000810100011234567",
                    1234, LocalDate.now(), LocalDate.now().plusMonths(1));
            System.out.println("Проверка правильного баланса в дебетовом счёте:");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            // Правильный баланс
            CreditAccount test3 = new CreditAccount("44000810100011234567",
                    -1, LocalDate.now(), LocalDate.now().plusMonths(1), 5);
            System.out.println("Проверка правильного баланса в кредитном счёте счёте:");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\nПроверка LocalDate");
        try {
            CreditAccount test2 = new CreditAccount("44000810100011234567",
                    -1, LocalDate.now().plusMonths(1), LocalDate.now().plusMonths(2), 5);
            System.out.println("Дата создания в будущем:");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            CreditAccount test2 = new CreditAccount("44000810100011234567",
                    -1, LocalDate.now(), LocalDate.now().minusMonths(1), 5);
            System.out.println("Дата окончания обслуживания раньше даты создания:");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
        try {
            CreditAccount test3 = new CreditAccount("44000810100011234567", LocalDate.now().plusMonths(1));
            System.out.println("Правильная дата:");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\nПроверка размера и даты следующего платежа");
        try {
            CreditAccount test3 = new CreditAccount("44000810100011234567",
                    -1, LocalDate.now(), LocalDate.now().plusMonths(24), 15);
            System.out.println("Сколько платить через 24 месяца:");
            System.out.println(test3.getNextPaymentDate() + " - " + test3.getNextPaymentValue());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            CreditAccount test3 = new CreditAccount("44000810100011234567",
                    -1, LocalDate.now(), LocalDate.now().plusMonths(10), 5);
            System.out.println("Сколько платить через 10 месяцев:");
            System.out.println(test3.getNextPaymentDate() + " - " + test3.getNextPaymentValue());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            CreditAccount test3 = new CreditAccount("44000810100011234567",
                    -1, LocalDate.now(), LocalDate.now().plusMonths(10), 5);
            System.out.println("Сколько платить с процентом 5:");
            System.out.println(test3.getNextPaymentDate() + " - " + test3.getNextPaymentValue());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            CreditAccount test3 = new CreditAccount("44000810100011234567",
                    -1, LocalDate.now(), LocalDate.now().plusMonths(24), 15);
            System.out.println("Сколько платить с процентом 15:");
            System.out.println(test3.getNextPaymentDate() + " - " + test3.getNextPaymentValue());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println("\nПроверка класса Entity)");
            Entity e = new Entity("Денис");
            e.add(new CreditAccount("44000810100011234567", LocalDate.now().plusMonths(1)));
            e.add(new DebitAccount("40000810100011234567", LocalDate.now().plusMonths(1)));
            e.add(new DebitAccount("40000810100011234568", LocalDate.now().plusMonths(1)));
            e.add(new DebitAccount("40000810100011234569", LocalDate.now().plusMonths(1)));
            try {
                System.out.println(e.get("44000810100011234567"));
                System.out.println("Проверка на существующий номер:");
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
            try {
                System.out.println(e.get("44000810100011234566"));
                System.out.println("Проверка на несуществующий номер:");
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
            try {
                System.out.println("Проверка на добавление существующего номера");
                System.out.println(
                        e.add(new CreditAccount("44000810100011234567", LocalDate.now().plusMonths(1)))
                );
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
            try {
                System.out.println("Проверка на установку сущестующего номера:");
                e.set(3, new CreditAccount("44000810100011234567", LocalDate.now().plusMonths(1)));
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
            try {
                System.out.println("Проверка на индекс меньше нуля:");
                System.out.println(e.get(-1));
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
            try {
                System.out.println("Проверка на индекс больше размера массива");
                System.out.println(e.get(10));
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    /*}
    /*public  static  void lab4tests(){
        System.out.println("Тестирование методов класса DebitAccount:");
        DebitAccount test = new DebitAccount("123",12.3);
        DebitAccount test2 = new DebitAccount("123",12.3);
        DebitAccount test3 = new DebitAccount("321",12.3);
        DebitAccount test4 = new DebitAccount("123",32.1);
        System.out.println(test.toString());
        System.out.println(test.hashCode());
        System.out.println(test.equals(test2));
        System.out.println(test.equals(test3));
        System.out.println(test.equals(test4));
        System.out.println("Тестирование методов класса Individual:");
        Individual itest = new Individual("Denis");
        Individual itest2 = new Individual("Denis");
        Individual itest3 = new Individual("Kirill");
        Individual itest4 = new Individual("Denis");
        itest.add(test3);
        itest.add(test2);
        itest2.add(test3);
        itest2.add(test2);
        itest4.add(test);
        System.out.println(itest.toString());
        System.out.println(itest.hashCode());
        System.out.println(itest.equals(itest2));
        System.out.println(itest.equals(itest3));
        System.out.println(itest.equals(itest4));
        System.out.println("Тестирование методов класса Entity:");
        Entity etest = new Entity("Denis");
        Entity etest2 = new Entity("Denis");
        Entity etest3 = new Entity("Kirill");
        Entity etest4 = new Entity("Denis");
        etest.add(test2);
        etest2.add(test2);
        etest4.add(test3);
        System.out.println(etest.toString());
        System.out.println(etest.hashCode());
        System.out.println(etest.equals(etest2));
        System.out.println(etest.equals(etest3));
        System.out.println(etest.equals(etest4));
        System.out.println(etest.totalBalance());
        System.out.println("Тестирование методов класса AccountManager:");
        AccountManager atest = new AccountManager();
        atest.add(itest);
        atest.add(etest);
        System.out.println(atest.remove(0));
        System.out.println(atest.indexOf(etest));
    }
    public  static void lab3tests(){
    System.out.println("Тестирование наследований класса AbstractAccount:");
    DebitAccount test = new DebitAccount("123",12.3);
    System.out.println("Номер: "+test.getNumber()+"Баланс: "+test.getBalance());
    test.setBalance(32.1);
    test.setNumber("321");
    System.out.println("Номер: "+test.getNumber()+"Баланс: "+test.getBalance());
    System.out.println("Тестирование изменений интерфейса Client:");
    Entity test1=new Entity("Денис");
    System.out.println("4исло кредитов: "+test1.getCreditScore());
    Entity test2 = new Entity("Проверка ");
    test2.addCreditScores(3);
    System.out.println("Увели4ение кредитныъ о4ков:"+test2.getCreditScore());
    System.out.println("Статус:"+test2.getStatus());
    }

    public static void lab2tests(){

        System.out.println("Проверка интерфейса Account:");
        Account a1 = new DebitAccount();
        Account a2 = new DebitAccount("4000",10.5);
        Account a3= new DebitAccount("123",12.3);
        System.out.println("Номер:"+a2.getNumber()+" Баланс:"+a2.getBalance());
        a1.setBalance(13.03);
        a1.setNumber("2020");
        System.out.println("Номер:"+ a1.getNumber()+" Баланс:"+a1.getBalance());

        System.out.println("\nПроверка интерфейса Client:");
        Client i1 = new Individual();
        i1.set(1,a1);
        i1.add(6,a2);
        i1.add(a3);
        Client i2 = new Individual(i1.getAccounts());
        System.out.println("Метод на добавление счёта в первое свободное место и в заданное место:");
        Account[] accounts;
        accounts = i2.getAccounts();
        for (Account account : accounts) {
            System.out.println(account.getNumber() + " " + account.getBalance());
        }
        System.out.println("возвращающий суммарный баланс всех счетов:");
        System.out.println("Сумма всех счётов: "+i1.totalBalance());
        System.out.println("Метод сортировки по балансу:");
        accounts = i1.sortedAccountsByBalance();
        for (Account account : accounts) {
            System.out.println(account.getNumber() + " " + account.getBalance());
        }

        System.out.println("\nПроверка класса Entity:");
        Entity c1 = new Entity("Олег");
        c1.set(1,a1);
        c1.add(6,a2);
        c1.add(a3);
        Entity c2 = new Entity("Кирилл",c1.getAccounts());
        System.out.println("Метод на добавление счёта в первое свободное место и в заданное место:");
        Account[] accounts2;
        accounts2 = c2.getAccounts();
        for (Account account : accounts2) {
            if(account!=null){
                System.out.println(account.getNumber() + " " + account.getBalance());
            }
        }
        System.out.println("возвращающий суммарный баланс всех счетов:");
        System.out.println("Сумма всех счётов: "+c1.totalBalance());
        System.out.println("Метод сортировки по балансу:");
        accounts2 = c1.sortedAccountsByBalance();
        for (Account account:accounts2) {
            System.out.println(account.getNumber() + " " + account.getBalance());
        }

        System.out.println("Проверка класса Account Manager:");
        AccountManager accountManager = new AccountManager();
        accountManager.add(i1);
        accountManager.add(i2);
        Client[] clients;
        clients = accountManager.getClients();
        for (int i = 0; i < clients.length; ++i) {
            System.out.println("Client  " + (i + 1) + ":");
            accounts = clients[i].getAccounts();
            for (Account account : accounts) {
                System.out.println(account.getNumber() + " " + account.getBalance());
            }
        }
        System.out.println();
    }
    public static void lab1tests() {
        System.out.println("Проверка класса Account:");
        DebitAccount person = new DebitAccount();
        DebitAccount a1 = new DebitAccount();
        DebitAccount a2 = new DebitAccount("4000",100);
        person.setBalance(53.23);
        person.setNumber("4000");
        System.out.println("Номер: " + person.getNumber() + "Баланс: " + person.getBalance());
        DebitAccount person1 = new DebitAccount("3221", 40);
        System.out.println("Номер: " + person1.getNumber() + "Баланс: " + person1.getBalance());

        Individual i1 = new Individual();
        Individual i2 = new Individual(3);
        i2.set(1,a1);
        i2.add(a2);
        Individual i3 = new Individual(i2.getAccounts());
        Account[] accounts;
        i1.add(new DebitAccount("5637", 954));
        accounts = i1.getAccounts();
        System.out.println("\nПроверка методов Individual:");
        System.out.println("Individual 1:");
        for (Account account3 : accounts) {
            System.out.println(account3.getNumber() + " " + account3.getBalance());
        }
        System.out.println("\nIndividual 2:");
        accounts = i2.getAccounts();

        for (Account account2 : accounts) {
            System.out.println(account2.getNumber() + " " + account2.getBalance());
        }

        accounts = i3.getAccounts();
        System.out.println("\nIndividual 3:");
        for (Account account1 : accounts) {
            System.out.println(account1.getNumber() + " " + account1.getBalance());
        }
        System.out.println("Удаление аккаунта 5637 из i1 и добавление его i2");
        i2.add(i1.remove("5637"));
        i3.set(0 , new DebitAccount("2001", 500));

        accounts = i1.getAccounts();
        System.out.println("Individual 1:");
        for (Account element : accounts) {
            System.out.println(element.getNumber() + " " + element.getBalance());
        }
        accounts = i2.getAccounts();
        System.out.println("\nIndividual 2:");


        for (Account item : accounts) {

            System.out.println(item.getNumber() + " " + item.getBalance());
        }

        accounts = i3.getAccounts();
        System.out.println("\nIndividual 3:");
        for (Account value : accounts) {
            System.out.println(value.getNumber() + " " + value.getBalance());
        }
        System.out.println();

        System.out.println("Проверка класса Account Manager:");
        AccountManager accountManager = new AccountManager();
        accountManager.add(i1);
        accountManager.add(i2);
        accountManager.add(i3);
        Individual[] individuals;
    }
    */
