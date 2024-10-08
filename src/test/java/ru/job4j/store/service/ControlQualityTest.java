package ru.job4j.store.service;

import org.junit.jupiter.api.Test;
import ru.job4j.store.entity.*;
import ru.job4j.store.store.Shop;
import ru.job4j.store.store.Store;
import ru.job4j.store.store.Trash;
import ru.job4j.store.store.Warehouse;

import java.util.List;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ControlQualityTest {

    @Test
    public void whenDistributeFoodThenCorrectStore() {

        Food milk = new Milk(
                "Milk",
                LocalDate.now().minusDays(10),
                LocalDate.now().minusDays(30),
                5.0,
                0);
        Food meat = new Meat(
                "Turkey",
                LocalDate.now().plusDays(20),
                LocalDate.now().minusDays(5),
                3.0,
                0);
        Food apple = new Fruit(
                "Apple",
                LocalDate.now().plusDays(10),
                LocalDate.now().minusDays(10),
                2.0,
                0);
        Food potato = new Vegetable(
                "Potato",
                LocalDate.now().plusDays(10),
                LocalDate.now().minusDays(40),
                8.0,
                0);

        Store trash = new Trash();
        Store shop = new Shop();
        Store warehouse = new Warehouse();

        ControlQuality controlQuality = new ControlQuality(List.of(trash, shop, warehouse));

        controlQuality.distribute(milk);
        controlQuality.distribute(meat);
        controlQuality.distribute(apple);
        controlQuality.distribute(potato);

        assertTrue(trash.findAll().contains(milk));
        assertFalse(warehouse.findAll().contains(milk));
        assertFalse(shop.findAll().contains(milk));

        assertTrue(warehouse.findAll().contains(meat));
        assertFalse(trash.findAll().contains(meat));
        assertFalse(shop.findAll().contains(meat));

        assertTrue(shop.findAll().contains(apple));
        assertFalse(trash.findAll().contains(apple));
        assertFalse(warehouse.findAll().contains(apple));

        assertTrue(shop.findAll().contains(potato));
        assertEquals(0.20, potato.getDiscount());
        assertEquals(6.4, potato.getPrice(), 0.01);
        assertFalse(trash.findAll().contains(potato));
        assertFalse(warehouse.findAll().contains(potato));
    }

}