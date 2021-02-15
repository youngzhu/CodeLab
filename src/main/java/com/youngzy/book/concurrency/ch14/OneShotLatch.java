package com.youngzy.book.concurrency.ch14;


import java.util.concurrent.locks.*;

/**
 * 14-14 使用 AQS（Abstract Queued Synchronizer）实现的二元闭锁
 */
public class OneShotLatch {
    private final Sync sync = new Sync();

    public void signal() {
        sync.releaseShared(0);
    }

    public void await() throws InterruptedException {
        sync.acquireInterruptibly(0);
    }

    private class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected int tryAcquireShared(int ignored) {
            // 如果这个闭锁是开的（state == 1）则成功
            // 否则 失败
            return getState() == 1 ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int ignored) {
            setState(1); // 打开闭锁
            return true; // 其他线程可以获取该闭锁
        }
    }
}
