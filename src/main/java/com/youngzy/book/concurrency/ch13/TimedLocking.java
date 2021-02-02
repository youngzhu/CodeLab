package com.youngzy.book.concurrency.ch13;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 13-4 带有时间限制的加锁
 */
public class TimedLocking {
    private Lock lock = new ReentrantLock();

    public boolean trySendOnSharedLine(String msg, long timeout, TimeUnit unit)
        throws InterruptedException {
        long nanosToLock = unit.toNanos(timeout) - estimatedNanosToSend(msg);
        if (! lock.tryLock(nanosToLock, TimeUnit.NANOSECONDS))
            return false;
        try {
            return sendOnSharedLine(msg);
        } finally {
            lock.unlock();
        }
    }

    boolean sendOnSharedLine(String msg) {
        /* send something*/
        return true;
    }

    long estimatedNanosToSend(String msg) {
        return msg.length();
    }

}
