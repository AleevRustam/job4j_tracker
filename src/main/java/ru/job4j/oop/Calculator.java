package ru.job4j.oop;

public class Calculator {
    private static int x = 5;

    public static int sum(int y) {
        return x + y;
    }

    public int multiply(int a) {
        return x * a;
    }

    public int minus(int a) {
        return a - x;
    }

    public int divide(int a) {
        return a / x;
    }

    public int sumAllOperation(int a) {
        return sum(a) + multiply(a) + minus(a) + divide(a);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int a = 10;
        int resultSum = sum(a);
        int resultMinus = calculator.minus(a);
        int resultMultiply = calculator.multiply(a);
        int resultDivide = calculator.divide(a);
        int resultAllSum = calculator.sumAllOperation(a);
        System.out.println(resultSum);
        System.out.println(resultMinus);
        System.out.println(resultMultiply);
        System.out.println(resultDivide);
        System.out.println(resultAllSum);
    }
}
