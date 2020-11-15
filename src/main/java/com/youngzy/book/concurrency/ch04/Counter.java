package com.youngzy.book.concurrency.ch04;

/**
 * 4-1 使用Java监视器模式的 线程安全的计数器
 */
public class Counter {
    private long value = 0;

    public synchronized long getValue() {
        return value;
    }

    public synchronized long increment() {
        if (value == Long.MAX_VALUE) {
            throw new IllegalStateException("counter overflow");
        }
        return ++value;
    }
}
