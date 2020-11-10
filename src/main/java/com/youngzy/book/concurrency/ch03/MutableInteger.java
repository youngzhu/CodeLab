package com.youngzy.book.concurrency.ch03;

/**
 * 3-2 非线程安全的可变整数类
 */
public class MutableInteger {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
