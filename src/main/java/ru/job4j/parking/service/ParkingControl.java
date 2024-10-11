package ru.job4j.parking.service;

import ru.job4j.parking.entity.AbstractCar;
import ru.job4j.parking.entity.Parking;

public class ParkingControl {

    private Parking parking;

    public ParkingControl(Parking parking) {
        this.parking = parking;
    }

    public boolean addCar(AbstractCar car) {
        return parking.parkCar(car);
    }
}
