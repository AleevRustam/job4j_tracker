package ru.job4j.store.service;

import ru.job4j.store.entity.*;
import ru.job4j.store.store.Shop;
import ru.job4j.store.store.Store;
import ru.job4j.store.store.Trash;
import ru.job4j.store.store.Warehouse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void distribute(Food food) {
        for (Store store : stores) {
            if (store.accept(food)) {
                store.add(food);
                break;
            }
        }
    }

    public void resort() {
        List<Food> allFoods = new ArrayList<>();
        for (Store store : stores) {
            allFoods.addAll(store.findAll());
            List<Food> foods = store.findAll();
            for (Food food : foods) {
                store.delete(food.getName());
            }
        }

        for (Food food : allFoods) {
            distribute(food);
        }
    }

    public static void main(String[] args) {
        Food meat = new Meat(
                "Мясо",
                LocalDate.now().plusDays(20),
                LocalDate.now().minusDays(5),
                3.0,
                0);
        Food fruit = new Fruit(
                "Яблоко",
                LocalDate.now().plusDays(10),
                LocalDate.now().minusDays(10),
                2.0,
                0);
        Food vegetable = new Vegetable(
                "Картофель",
                LocalDate.now().plusDays(10),
                LocalDate.now().minusDays(40),
                8.0,
                0);
        Food milk = new Milk(
                "Молоко",
                LocalDate.now().minusDays(10),
                LocalDate.now().minusDays(30),
                5.0,
                0);

        Store trash = new Trash();
        Store shop = new Shop();
        Store warehouse = new Warehouse();

        ControlQuality controlQuality = new ControlQuality(List.of(trash, shop, warehouse));

        controlQuality.distribute(milk);
        controlQuality.distribute(meat);
        controlQuality.distribute(fruit);
        controlQuality.distribute(vegetable);

        System.out.println("До пересортировки:");
        System.out.println("Warehouse: " + warehouse.findAll());
        System.out.println("Shop: " + shop.findAll());
        System.out.println("Trash: " + trash.findAll());

        fruit.setExpiryDate(LocalDate.now().minusDays(1));

        controlQuality.resort();

        System.out.println("После пересортировки:");
        System.out.println("Warehouse: " + warehouse.findAll());
        System.out.println("Shop: " + shop.findAll());
        System.out.println("Trash: " + trash.findAll());
    }
}
