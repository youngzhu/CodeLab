package com.youngzy.book.concurrency.ch03;

/**
 * 3-15 由于不安全的发布，这个类可能出现故障
 */
public class Holder {
    // 如果定义成 final ，可以避免这样的问题。这样它就成了 不可变对象
    private int n;

    public Holder(int n) {
        this.n = n;
    }

    public void assertSanity() {
        if (n != n) {
            throw new AssertionError("This statement is false");
        }
    }
}
