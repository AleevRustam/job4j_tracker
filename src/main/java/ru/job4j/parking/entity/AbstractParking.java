package ru.job4j.parking.entity;

import java.util.ArrayList;

public class AbstractParking implements Parking {

    @Override
    public ArrayList<Boolean> getSpot() {
        return null;
    }

    @Override
    public boolean parkCar(AbstractCar car) {
        return false;
    }
}
