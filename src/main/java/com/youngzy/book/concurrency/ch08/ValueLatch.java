package com.youngzy.book.concurrency.ch08;

import java.util.concurrent.CountDownLatch;

/**
 * 8-17 携带结果的闭锁
 * 用来确保答案只会被设置一次
 *
 * @param <T>
 */
public class ValueLatch<T> {
    private T value = null;
    private final CountDownLatch done = new CountDownLatch(1);

    public boolean isSet() {
        return done.getCount() == 0;
    }

    public synchronized void setValue(T newValue) {
        if (! isSet()) {
            value = newValue;
            done.countDown();
        }
    }

    public T getValue() throws InterruptedException {
        done.await();
        synchronized (this) {
            return value;
        }
    }
}
