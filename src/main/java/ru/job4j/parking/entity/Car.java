package ru.job4j.parking.entity;

public class Car extends AbstractCar {

    public Car(int size) {
        super(size);
    }

    @Override
    public int getSize() {
        return 1;
    }
}
