package ru.job4j.polymorphism;

public class Bus implements Transport {
    @Override
    public void go() {
        System.out.println("Автобус едет аккуратно.");
    }

    @Override
    public void passengers(int numbersOfPassengers) {
        System.out.printf("Автобус перевозит %d пассажиров.", numbersOfPassengers);
    }

    @Override
    public double refuel(double fuelAmount) {
        double price = fuelAmount * 64;
        return price;
    }
}
