package com.youngzy.book.concurrency.ch02;

/**
 * 2-7 如果内置锁不可重入，这段代码将发生死锁
 */
public class LoggingWidget extends Widget {
    public synchronized void doSomething() {
        System.out.println(toString() + ": calling doSomething");
        super.doSomething();
    }
}

class Widget {
    public synchronized void doSomething() {}
}