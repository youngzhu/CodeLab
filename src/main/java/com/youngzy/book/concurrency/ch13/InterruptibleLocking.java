package com.youngzy.book.concurrency.ch13;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 13-5 可中断的锁获取操作
 */
public class InterruptibleLocking {
    private Lock lock = new ReentrantLock();

    public boolean sendOnSharedLine(String msg) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            return cancellableSendOnSharedLine(msg);
        } finally {
            lock.unlock();
        }

    }

    private boolean cancellableSendOnSharedLine(String msg) {
        /* send something */
        return true;
    }
}
