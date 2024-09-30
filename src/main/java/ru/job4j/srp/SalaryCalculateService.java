package ru.job4j.srp;

public class SalaryCalculateService {
    public double calculateSalary(int hoursWorked, double hourlyRate) {
        return hoursWorked * hourlyRate;
    }

    public void printSalary(double salary) {
        System.out.println("Зарплата: " + salary);
    }
}
