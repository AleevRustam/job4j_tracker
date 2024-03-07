package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс описывает работу простейшего банка:
 * добавление/удаление Пользователя, добавление/удаление счета, поиск по пасспорту/реквизитам,
 * перевод средств с одного счета на другой
 * @author Rustam Aleev
 * @version 1.0
 */
public class BankService {
    /**
     * Хранение пользователей осуществляется в коллекции типа HashMap
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод принимает на вход пользователя и добавляет его в коллекцию
     * @param user Пользователь, который добавляется в коллекцию
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод принимает на вход Пользователя, которого необходимо удалить из коллекции
     * @param passport Пользователь, который удаляется из коллекции
     */
    public void deleteUser(String passport) {
        users.remove(new User(passport, ""));
    }

    /**
     * Метод принимает на вход пасспорт и счет. По паспорту ищется пользователь,
     * которому добаляется новый счет
     * @param passport Пасспорт, по которому ищется Пользователь
     * @param account Счет, который добавляется найденному Пользователю
     */
    public void addAccount(String passport, Account account) {
        User key = findByPassport(passport);
        if (key != null) {
            List<Account> accounts = getAccounts(key);

            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    /**
     * Метод поиска Пользователя по пасспорту
     * @param passport Паспорт для поиска Пользователя
     * @return возвращает Пользователя из коллекции или null, если такого Пользователя не существует
     */
    public User findByPassport(String passport) {
        User result = null;
        for (User user : users.keySet()) {
            if (passport.equals(user.getPassport())) {
                result = user;
                break;
            }
        }
        return result;
    }

    /**
     * Метод поиска Пользователя по реквизитам
     * @param requisite Реквизиты для поиска Пользователя
     * @return возвращает Пользователя из коллекции или null, если такого Пользователя не существует
     */
    public Account findByRequisite(String passport, String requisite) {
        Account result = null;
        User key = findByPassport(passport);
        if (key != null) {
            List<Account> accounts = getAccounts(key);
            for (Account account : accounts) {
                if (requisite.equals(account.getRequisite())) {
                    result = account;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Метод для перевода денежных средств с одного счета на другой счет
     * @param sourcePassport Паспорт для поиска Ползователя, со счета которого будут переводится деньги
     * @param sourceRequisite Реквизиты счета которого будут переводится деньги
     * @param destinationPassport Паспорт для поиска Ползователя, на счет которого будут переводится деньги
     * @param destinationRequisite Реквизиты счета на который будут переводится деньги
     * @param amount Сумма перевода
     * @return возвращает true если перевод завершился успешно или false если произошла ошибка
     * */
    public boolean transferMoney(String sourcePassport, String sourceRequisite,
                                 String destinationPassport, String destinationRequisite,
                                 double amount) {
        Account src = findByRequisite(sourcePassport, sourceRequisite);
        Account dest = findByRequisite(destinationPassport, destinationRequisite);
        if (src == null || dest == null || src.getBalance() < amount) {
            return false;
        }
        src.setBalance(src.getBalance() - amount);
        dest.setBalance(dest.getBalance() + amount);
        return true;

    }

    /**
     * Метод позволяет получить все счета Пользователя
     * @param user Пользователь, счета которого нужно получить
     * @return Возвращает список счетов Пользователя
     */
    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}
