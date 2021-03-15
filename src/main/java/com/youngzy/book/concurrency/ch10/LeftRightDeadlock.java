package com.youngzy.book.concurrency.ch10;

/**
 * 10-1 简单的锁顺序死锁（不要这么做）
 */
// 注意：容易发生死锁
public class LeftRightDeadlock {
    private final Object left = new Object();
    private final Object right = new Object();

    public void leftRight() {
        synchronized (left) {
            synchronized (right) {
                doSomething();
            }
        }
    }

    public void rightLeft() {
        synchronized (right) {
            synchronized (left) {
                doSomethingElse();
            }
        }
    }

    void doSomething() {}
    void doSomethingElse() {}
}
