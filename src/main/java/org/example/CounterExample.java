package org.example;

public class CounterExample {
    private final Object lock = new Object();
    private int count = 0;

    public void incrementAndPrint() {
        synchronized (lock){
            System.out.println(Thread.currentThread().getName() + "is incrementing..." + count++);
        }
    }
}
