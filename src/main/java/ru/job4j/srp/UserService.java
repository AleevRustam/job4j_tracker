package ru.job4j.srp;

public class UserService {
    public void addUser(String username) {
        System.out.println("Пользователь " + username + " добавлен.");

        log("Добавлен новый пользователь: " + username);
    }

    private void log(String message) {
        System.out.println("LOG: " + message);
    }
}
