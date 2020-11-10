package com.youngzy.book.concurrency.ch03;

/**
 * 3-3 线程安全的可变整数类
 *
 * 仅对set方法同步是不够的
 * 调用get的线程仍然可能看见失效值
 */
public class SynchronizedInteger {
    private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized void setValue(int value) {
        this.value = value;
    }
}
