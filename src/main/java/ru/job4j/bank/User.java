package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс описывает сущность Пользователь
 * @author Rustam Aleev
 * @version 1.0
 */
public class User {
    /**
     * поле паспорт
     */
    private String passport;
    /**
     * поле имя пользователя
     */
    private String username;

    /**
     * конструктор, принимающий 2 параметра
     * @param passport пасспорт Пользователя
     * @param username имя Пользователя
     */
    public User(String passport, String username) {
        this.passport = passport;
        this.username = username;
    }

    /**
     * геттер пасспорта
     * @return возвращает пасспорт
     */
    public String getPassport() {
        return passport;
    }

    /**
     * сеттер пасспорта
     * @param passport устанавливает новое значение пасспорта
     */
    public void setPassport(String passport) {
        this.passport = passport;
    }

    /**
     * геттер имени Пользователя
     * @return возвращает имя Пользователя
     */
    public String getUsername() {
        return username;
    }

    /**
     * сеттер имени Пользователя
     * @param username устанавливает новое значение Пользователя
     */
    public void setUsername(String username) {
        this.username = username;
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
        User user = (User) o;
        return Objects.equals(passport, user.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }
}
