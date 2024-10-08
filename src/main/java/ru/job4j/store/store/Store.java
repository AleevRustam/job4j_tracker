package ru.job4j.store.store;

import ru.job4j.store.entity.Food;

import java.util.List;

public interface Store extends AutoCloseable {

    boolean accept(Food food);

    Food add(Food food);

    boolean replace(String name, Food updateFood);

    void delete(String name);

    List<Food> findAll();

    List<Food> findByName(String key);
}
