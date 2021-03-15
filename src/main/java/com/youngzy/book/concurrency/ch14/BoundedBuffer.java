package com.youngzy.book.concurrency.ch14;

import com.youngzy.book.concurrency.ch08.BoundedExecutor;

/**
 * 14-6 使用条件队列实现的有界缓存
 */
public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {
    public BoundedBuffer() {
        this(100);
    }

    protected BoundedBuffer(int capacity) {
        super(capacity);
    }

    public synchronized void put(V v) throws InterruptedException {
        // 阻塞，直到 not full
        while (isFull())
            wait();
        doPut(v);
        notifyAll();
    }

    public synchronized V take() throws InterruptedException {
        // 阻塞，直到 not empty
        while (isEmpty())
            wait();
        V v = take();
        notifyAll();
        return v;
    }

    /**
     * 14-8 另一种形式的put，有条件地唤醒
     *
     * @param v
     * @throws InterruptedException
     */
    public synchronized void alternatePut(V v) throws InterruptedException {
        while (isFull())
            wait();
        boolean wasEmpty = isEmpty();
        doPut(v);
        if (wasEmpty)
            notifyAll();
    }
}
