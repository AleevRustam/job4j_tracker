package ru.job4j.store.entity;

import java.time.LocalDate;

public class Fruit extends Food {
    public Fruit(String name, LocalDate expiryDate, LocalDate createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
