package com.youngzy.book.concurrency.ch15;

/**
 * 15-2 基于CAS实现的非阻塞计数器
 */
public class CASCounter {
    private SimulatedCAS value;

    public int getValue() {
        return value.get();
    }

    public int increment() {
        int v;
        do {
            v = value.get();
        } while (v != value.compareAndSwap(v, v + 1));
        return v + 1;
    }
}
