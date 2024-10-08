package ru.job4j.store.store;

import org.junit.jupiter.api.Test;
import ru.job4j.store.entity.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ShopTest {

    @Test
    public void whenProductIsExpiredLessThan75PercentThenGoesToShop() {
        Food apple = new Fruit(
                "Apple",
                LocalDate.now().plusDays(10),
                LocalDate.now().minusDays(10),
                2.0, 0
        );
        Shop shop = new Shop();

        assertTrue(shop.accept(apple));
        shop.add(apple);
        assertTrue(shop.findAll().contains(apple));
        assertEquals(0.0, apple.getDiscount());
    }

    @Test
    public void whenProductIsExpiredMoreThan75PercentThenGetsDiscount() {
        Food potato = new Vegetable(
                "Potato",
                LocalDate.now().plusDays(10),
                LocalDate.now().minusDays(40),
                8.0,
                0);

        Shop shop = new Shop();

        assertTrue(shop.accept(potato));
        shop.add(potato);
        assertTrue(shop.findAll().contains(potato));
        assertEquals(0.20, potato.getDiscount());
        assertEquals(6.4, potato.getPrice(), 0.01);
    }

    @Test
    public void whenProductIsExpiredMoreThan100PercentThenNotInShop() {
        Food milk = new Milk(
                "Milk",
                LocalDate.now().minusDays(10),
                LocalDate.now().minusDays(30),
                5.0,
                0);
        Shop shop = new Shop();

        assertFalse(shop.accept(milk));
    }
}