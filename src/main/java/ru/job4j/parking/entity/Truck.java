package ru.job4j.parking.entity;

public class Truck extends AbstractCar {

    private int size;

    public Truck(int size) {
        super(size);
    }

    public int getSize() {
        return size;
    }
}
