package ru.job4j.store.store;

import org.junit.jupiter.api.Test;
import ru.job4j.store.entity.Food;
import ru.job4j.store.entity.Meat;
import ru.job4j.store.entity.Milk;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WarehouseTest {

    @Test
    public void whenProductIsFreshThenGoesToWarehouse() {
        Food meat = new Meat(
                "Beef",
                LocalDate.now().plusDays(20),
                LocalDate.now().minusDays(5),
                3.0,
                0);
        Warehouse warehouse = new Warehouse();
        assertTrue(warehouse.accept(meat));
        warehouse.add(meat);
        assertTrue(warehouse.findAll().contains(meat));
    }

    @Test
    public void whenProductIsNotFreshThenNotGoesToWarehouse() {
        Food milk = new Milk(
                "Milk",
                LocalDate.now().minusDays(10),
                LocalDate.now().minusDays(30),
                5.0,
                0);
        Warehouse warehouse = new Warehouse();

        assertFalse(warehouse.accept(milk));
    }

}