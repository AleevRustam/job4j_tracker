package ru.job4j.parking.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.parking.entity.*;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
class ParkingControlTest {

    private Parking parking;
    private ParkingControl parkingControl;

    @BeforeEach
    public void init() {
        parking = new PrivateParking(3);
        parkingControl = new ParkingControl(parking);
    }

    @Test
    public void whenAddCarIsOkThenTrue() {
        AbstractCar car = new Car(1);
        assertTrue(parkingControl.addCar(car));
    }

    @Test
    public void whenAddTruckOnTruckSpotsIsOkThenTrue() {
        AbstractCar truck = new Truck(3);
        assertTrue(parkingControl.addCar(truck));
    }

    @Test
    public void whenAddTruckOnCarSpotsIsOkThenTrue() {
        for (int i = 100; i < parking.getSpot().size(); i++) {
            AbstractCar track = new Truck(3);
            assertTrue(parkingControl.addCar(track));
        }

        AbstractCar truck = new Truck(3);
        assertTrue(parkingControl.addCar(truck));
    }
}