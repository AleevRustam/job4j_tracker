package ru.job4j.parking.entity;

import java.util.List;

public interface Parking {

    List<Boolean> getSpot();

    boolean parkCar(AbstractCar car);
}
