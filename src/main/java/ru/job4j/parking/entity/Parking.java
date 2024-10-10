package ru.job4j.parking.entity;

import java.util.ArrayList;

public interface Parking {
    ArrayList<Boolean> getSpot();

    boolean parkCar(AbstractCar car);

//    int getAvailableCarSpots();
//
//    int getAvailableTruckSpots();
}
