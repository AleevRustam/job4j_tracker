package ru.job4j.ood.ocp;

class Shape {
    public String type;

    public Shape(String type) {
        this.type = type;
    }
}

class AreaCalculator {
    public double calculateArea(Shape shape) {
        if (shape.type.equals("Circle")) {
            return Math.PI * 5 * 5;
        } else if (shape.type.equals("Square")) {
            return 5 * 5;
        }
        return 0;
    }
}

