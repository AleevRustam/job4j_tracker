package ru.job4j.store.store;

import ru.job4j.store.entity.Food;

public class Warehouse extends AbstractStore {
    @Override
    public boolean accept(Food food) {
        return food.getStorageTimePercentage() >= 0 && food.getStorageTimePercentage() < 25;
    }
}
