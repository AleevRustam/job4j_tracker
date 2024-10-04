package ru.job4j.ood.lsp;

public class Bird {
    public void fly() {
        System.out.println("Птица летит");
    }
}

class Ostrich extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Страус не может летать");
    }
}
