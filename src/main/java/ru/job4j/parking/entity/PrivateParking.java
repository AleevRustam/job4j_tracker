package ru.job4j.parking.entity;

import java.util.ArrayList;

public class PrivateParking extends AbstractParking {

    private int truckSize;
    private ArrayList<Boolean> spots;

    public PrivateParking(int truckSize) {
        this.truckSize = truckSize;
        spots = new ArrayList<>(100 + 100 * truckSize);
    }
}
