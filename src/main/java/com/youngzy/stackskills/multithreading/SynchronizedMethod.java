package com.youngzy.stackskills.multithreading;

public class SynchronizedMethod {
    private int counter;

    public synchronized void increase() {
        counter ++;
    }

    public synchronized void decrease() {
        counter --;
    }

    public synchronized int value() {
        return counter;
    }
}
