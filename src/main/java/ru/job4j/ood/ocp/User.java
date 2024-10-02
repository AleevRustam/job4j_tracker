package ru.job4j.ood.ocp;

public class User {
    String name;
    String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

class UserValidator {
    public boolean validate(User user) {
        if (user.name == null || user.name.isEmpty()) {
            return false;
        }
        if (user.email == null || user.email.isEmpty()) {
            return false;
        }
        return true;
    }
}
