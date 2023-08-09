package ru.sobse.service;

import java.util.concurrent.atomic.AtomicLong;

public class Counter {
    private AtomicLong index;
    private static Counter counter;

    private Counter() {
        index = new AtomicLong(0);
    }

    public long increment() {
        long next = index.incrementAndGet();
        return next;
    }

    public static Counter getCounter() {
        if (counter == null) {
            synchronized (Counter.class) {
                if (counter == null) {
                    counter = new Counter();
                    return counter;
                }
            }
        }
        return counter;
    }
}
