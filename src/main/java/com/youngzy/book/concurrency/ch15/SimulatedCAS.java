package com.youngzy.book.concurrency.ch15;

/**
 * 15-1 模拟CAS（Compare and Swap）操作
 * 本例是为了说明CAS的语义，而不是实现或性能
 */
public class SimulatedCAS {
    private int value;

    public synchronized int get() {
        return value;
    }

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue)
            value = newValue;
        return oldValue;
    }

    public synchronized boolean compareAndSet(int expectedValue, int newValue) {
        return expectedValue == compareAndSwap(expectedValue, newValue);
    }
}
