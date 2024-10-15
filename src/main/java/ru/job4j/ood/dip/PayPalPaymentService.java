package ru.job4j.ood.dip;

public class PayPalPaymentService {
    public void processPayment(double amount) {
        System.out.println("Processing payment via PayPal: $" + amount);
    }
}

class PaymentProcessor {
    private PayPalPaymentService paymentService;

    public PaymentProcessor() {
        this.paymentService = new PayPalPaymentService();
    }

    public void process(double amount) {
        paymentService.processPayment(amount);
    }
}

