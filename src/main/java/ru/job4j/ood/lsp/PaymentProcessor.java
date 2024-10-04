package ru.job4j.ood.lsp;

public class PaymentProcessor {
    public void pay(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Сумма не может быть отрицательной");
        }
        System.out.println("Платеж " + amount + " обработан");
    }
}

class FreePaymentProcessor extends PaymentProcessor {
    @Override
    public void pay(double amount) {
        if (amount != 0) {
            throw new IllegalArgumentException("Для 0 перевод бесплатный");
        }
        System.out.println("Бесплатный платеж проведен");
    }
}
