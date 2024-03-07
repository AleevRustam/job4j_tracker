package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс описывает сущность Счет
 * @author Rustam Aleev
 * @version 1.0
 */

public class Account {
    /**
     * поле реквизиты
     */
    private String requisite;
    /**
     * поле баланс
     */
    private double balance;

    /**
     * конструктор, принимающий 2 параметра
     * @param requisite реквизиты счета
     * @param balance баланс счета
     */
    public Account(String requisite, double balance) {
        this.requisite = requisite;
        this.balance = balance;
    }

    /**
     * геттер реквизитов
     * @return возвращает реквизиты
     */
    public String getRequisite() {
        return requisite;
    }

    /**
     * сеттер реквизитов
     * @param requisite новые реквизиты для счета
     */
    public void setRequisite(String requisite) {
        this.requisite = requisite;
    }

    /**
     * геттер баланса
     * @return возвращает баланс счета
     */
    public double getBalance() {
        return balance;
    }

    /**
     * сеттер баланса
     * @param balance устанавливает новый баланс счета
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * метод для сравнения объекта @this с объектом o
     * @param o объект для сравнения
     * @return возвращается булево значение
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(requisite, account.requisite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisite);
    }
}
