package com.youngzy.book.concurrency.ch04;


import com.youngzy.book.concurrency.common.Widget;

/**
 * 4-3 通过一个私有锁来保护状态
 */
public class PrivateLock {
    private final Object myLock = new Object();
    Widget widget;

    void someMethod() {
        // 通过 myLock 保护 widget
        synchronized (myLock) {
            // 访问或修改widget的状态
        }
    }
}
