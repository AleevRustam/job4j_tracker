package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {
    private final Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    public void deleteUser(String passport) {
        User key = findByPassport(passport);
        users.remove(key);
    }

    public void addAccount(String passport, Account account) {
        User key = findByPassport(passport);
        if (key != null) {
            List<Account> accounts = getAccounts(key);

            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    public User findByPassport(String passport) {
        User result = null;
        for (Map.Entry<User, List<Account>> entry : users.entrySet()) {
            if (passport.equals(entry.getKey().getPassport())) {
                result = entry.getKey();
            }
        }
        return result;
    }

    public Account findByRequisite(String passport, String requisite) {
        Account result = null;
        User key = findByPassport(passport);
        if (key != null) {
            List<Account> accounts = getAccounts(key);
            for (Account account : accounts) {
                if (requisite.equals(account.getRequisite())) {
                    result = account;
                }
            }
        }
        return result;
    }

    public boolean transferMoney(String sourcePassport, String sourceRequisite,
                                 String destinationPassport, String destinationRequisite,
                                 double amount) {
        Account src = findByRequisite(sourcePassport, sourceRequisite);
        Account dest = findByRequisite(destinationPassport, destinationRequisite);
        if (src == null || dest == null || src.getBalance() < amount) {
            return false;
        } else {
            src.setBalance(src.getBalance() - amount);
            dest.setBalance(dest.getBalance() + amount);
            return true;
        }
    }

    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}
