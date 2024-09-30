package ru.job4j.srp;

public class UserRepository {
    public void saveUser(String username) {
        connectToDatabase();

        System.out.println("Сохранение пользователя " + username);
    }

    private void connectToDatabase() {
        System.out.println("Подключение к базе данных...");
    }
}
