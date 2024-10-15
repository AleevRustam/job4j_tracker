package ru.job4j.ood.dip;

public class EmailService {
    public void sendEmail(String message) {
        System.out.println("Sending email: " + message);
    }
}

class NotificationManager {
    private EmailService emailService;

    public NotificationManager() {
        this.emailService = new EmailService();
    }

    public void notify(String message) {
        emailService.sendEmail(message);
    }
}
