package com.youngzy.book.concurrency.ch08;

import java.util.concurrent.ThreadFactory;

/** 8-6 自定义的线程工厂
 */
public class MyThreadFactory implements ThreadFactory {
    private final String poolName;

    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new MyAppThread(r, poolName);
    }
}
