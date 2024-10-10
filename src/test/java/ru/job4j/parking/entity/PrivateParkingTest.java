package ru.job4j.parking.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
class PrivateParkingTest {

    private Parking parking;

    @BeforeEach
    public void init() {
        parking = new PrivateParking(3);
    }

    @Test
    public void whenParkingIsEmptyThenTrue() {
        AbstractCar car = new Car(1);
        assertTrue(parking.parkCar(car));
    }

    @Test
    public void whenParkingIsFullThenFalse() {
        for (int i = 0; i < parking.getSpot().size(); i++) {
            AbstractCar car = new Car(1);
            assertTrue(parking.parkCar(car));
        }
        AbstractCar car = new Car(1);
        assertFalse(parking.parkCar(car));
    }

    @Test
    public void whenTruckParkOnTruckSpotsThenTrue() {
        AbstractCar truck = new Truck(3);
        assertTrue(parking.parkCar(truck));
    }

    @Test
    public void whenTruckParkOnEmptyCarSpotsThenTrue() {
        for (int i = 100; i < parking.getSpot().size(); i++) {
            AbstractCar track = new Truck(3);
            assertTrue(parking.parkCar(track));
        }
        AbstractCar truck = new Truck(3);
        assertTrue(parking.parkCar(truck));
    }
}