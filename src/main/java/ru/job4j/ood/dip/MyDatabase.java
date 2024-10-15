package ru.job4j.ood.dip;

public class MyDatabase {
    public void connect() {
        System.out.println("Connecting to My database...");
    }

    public void disconnect() {
        System.out.println("Disconnecting from My database...");
    }
}

class UserRepository {
    private MyDatabase database;

    public UserRepository() {
        this.database = new MyDatabase();
    }

    public void getUserById(int id) {
        database.connect();
        System.out.println("Fetching user with ID: " + id);
        database.disconnect();
    }
}
