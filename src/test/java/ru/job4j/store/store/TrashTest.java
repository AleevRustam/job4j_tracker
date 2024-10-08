package ru.job4j.store.store;

import org.junit.jupiter.api.Test;
import ru.job4j.store.entity.Food;
import ru.job4j.store.entity.Meat;
import ru.job4j.store.entity.Milk;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TrashTest {

    @Test
    public void whenProductIsExpiredItGoesToTrash() {
        Food milk = new Milk(
                "Milk",
                LocalDate.now().minusDays(10),
                LocalDate.now().minusDays(30),
                5.0,
                0);
        Trash trash = new Trash();

        assertTrue(trash.accept(milk));
        trash.add(milk);
        assertTrue(trash.findAll().contains(milk));
    }

    @Test
    public void whenProductIsNotExpiredItDoesNotGoToTrash() {
        Food meat = new Meat(
                "Pork",
                LocalDate.now().plusDays(20),
                LocalDate.now().minusDays(5),
                3.0,
                0);
        Trash trash = new Trash();

        assertFalse(trash.accept(meat));
    }
}