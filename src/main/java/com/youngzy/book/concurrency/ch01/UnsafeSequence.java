package com.youngzy.book.concurrency.ch01;

/**
 * 1-1 非线程安全的数值序列生成器
 */
public class UnsafeSequence {
    private int value;

    /**
     * 返回一个唯一的数值
     * @return
     */
    public int getNext() {
        return value++;
    }
}
