package ru.job4j.store.store;

import ru.job4j.store.entity.Food;

public class Shop extends AbstractStore {
    @Override
    public boolean accept(Food food) {
        double percentage = food.getStorageTimePercentage();
        return  percentage >= 25 && percentage < 100;
    }

    @Override
    public Food add(Food food) {
        double percentage = food.getStorageTimePercentage();
        if (percentage > 75 && percentage < 100) {
            food.setDiscount(0.20);
        }
        super.add(food);
        return food;
    }
}
