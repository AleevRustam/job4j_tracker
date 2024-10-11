package ru.job4j.parking.entity;

import java.util.List;

public abstract class AbstractParking implements Parking {

    private List<Boolean> spots;
    private int carSize;

    public AbstractParking(int carSize) {
        this.carSize = carSize;
    }

    @Override
    public List<Boolean> getSpot() {
        return spots;
    }

    public abstract boolean parkCar(AbstractCar car);
}
