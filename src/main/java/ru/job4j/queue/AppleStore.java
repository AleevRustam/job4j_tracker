package ru.job4j.queue;

import java.util.Queue;

public class AppleStore {
    private final Queue<Customer> queue;

    private final int count;

    public AppleStore(Queue<Customer> queue, int count) {
        this.queue = queue;
        this.count = count;
    }

    public String getLastHappyCustomer() {
        int totalValue = 0;
        Customer currentCustomer = null;
        for (int i = 0; i < queue.size(); i++) {
            currentCustomer = queue.poll();
            while (count - totalValue >= 0) {
                totalValue += currentCustomer.amount();
            }
        }
        return currentCustomer.name();
    }

    public String getFirstUpsetCustomer() {
        int totalValue = 0;
        Customer currentCustomer;
        for (int i = 0; i < queue.size(); i++) {
            currentCustomer = queue.poll();
            while (count - totalValue >= 0) {
                totalValue += currentCustomer.amount();
            }
        }
        return queue.poll().name();
    }
}
