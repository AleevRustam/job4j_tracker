package ru.job4j.cast;

public class Transports {
    public static void main(String[] args) {
        Vehicle plane = new Plane();
        Vehicle train = new Train();
        Vehicle bus = new Bus();

        Vehicle[] vehicles = new Vehicle[]{plane, train, bus};

        for (Vehicle vehicle : vehicles) {
            vehicle.move();
        }
    }
}
