package ru.job4j.store.store;

import ru.job4j.store.entity.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {

    private List<Food> foods = new ArrayList<>();

    @Override
    public abstract boolean accept(Food food);

    @Override
    public Food add(Food food) {
        foods.add(food);
        return food;
    }

    @Override
    public boolean replace(String name, Food updateFood) {
        int index = indexOf(name);
        boolean result = index != -1;
        if (result) {
            foods.set(index, updateFood);
        }
        return result;
    }

    @Override
    public void delete(String name) {
        int index = indexOf(name);
        if (index != -1) {
            foods.remove(index);
        }
    }

    @Override
    public List<Food> findAll() {
        return List.copyOf(foods);
    }

    @Override
    public List<Food> findByName(String key) {
        List<Food> result = new ArrayList<>();
        for (Food food : foods) {
            if (key.equals(food.getName())) {
                result.add(food);
            }
        }
        return result;
    }

    private int indexOf(String name) {
        int result = -1;
        for (int index = 0; index < foods.size(); index++) {
            if (foods.get(index).getName().equals(name)) {
                result = index;
                break;
            }
        }
        return result;
    }

    @Override
    public void close() throws Exception {

    }
}
