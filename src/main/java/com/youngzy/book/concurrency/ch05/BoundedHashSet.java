package com.youngzy.book.concurrency.ch05;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 5-14 使用信号量为容器设置边界
 */
public class BoundedHashSet<T> {
    private final Set<T> set;
    private final Semaphore semaphore;

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<>());
        semaphore = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
        semaphore.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = set.add(o);
            return wasAdded;
        } finally {
            if (! wasAdded) {
                semaphore.release();
            }
        }
    }

    public boolean remove(T o) {
        boolean wasRemoved = set.remove(o);
        if (wasRemoved) {
            semaphore.release();
        }
        return wasRemoved;
    }
}
