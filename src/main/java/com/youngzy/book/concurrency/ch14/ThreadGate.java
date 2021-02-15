package com.youngzy.book.concurrency.ch14;

/**
 * 14-9 使用 wait 和 notifyAll 实现可重新关闭的阀门
 */
public class ThreadGate {
    private boolean isOpen;
    private int generation;

    public synchronized void close() {
        isOpen = false;
    }

    public synchronized void open() {
        generation++;
        isOpen = true;
        notifyAll();
    }

    public synchronized void await() throws InterruptedException {
        int arrivalGeneration = generation;
        while (! isOpen && arrivalGeneration == generation)
            wait();
    }
}
