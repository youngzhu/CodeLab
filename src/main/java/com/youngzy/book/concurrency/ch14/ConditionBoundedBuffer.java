package com.youngzy.book.concurrency.ch14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 13-11 使用显示条件变量的有界缓存
 * @param <T>
 */
public class ConditionBoundedBuffer<T> {
    private final Lock lock = new ReentrantLock();
    // 条件谓词：非满 count < items.length
    private final Condition notFull = lock.newCondition();
    // 条件谓词：非空 count > 0
    private final Condition notEmpty = lock.newCondition();

    private static final int BUFFER_SIZE = 100;
    private final T[] items = (T[]) new Object[BUFFER_SIZE];
    private int tail, head, count;

    public void put(T t) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length)
                notFull.await();
            items[tail] = t;
            if (++tail == items.length)
                tail = 0;
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            T x = items[head];
            items[head] = null;
            if (++head == items.length)
                head = 0;
            --count;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }

}
