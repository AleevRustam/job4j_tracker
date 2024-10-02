package ru.job4j.ood.ocp;

public class PaymentProcessor {
    public void processPayment(String paymentType) {
        if (paymentType.equals("CreditCard")) {
            System.out.println("Processing credit card payment.");
        } else if (paymentType.equals("PayPal")) {
            System.out.println("Processing PayPal payment.");
        }
    }
}
