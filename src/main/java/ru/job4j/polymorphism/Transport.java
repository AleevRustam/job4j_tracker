package ru.job4j.polymorphism;

public interface Transport {
    void go();

    void passengers(int numbersOfPassengers);

    double refuel(double fuelAmount);
}
