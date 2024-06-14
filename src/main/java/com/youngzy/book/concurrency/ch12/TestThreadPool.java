package com.youngzy.book.concurrency.ch12;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.jupiter.api.Assertions.*;
/**
 * 12-9 验证线程池扩展能力的测试方法
 */
public class TestThreadPool {
    private final TestingThreadFactory factory = new TestingThreadFactory();

    public void testPoolExpansion() throws InterruptedException {
        int MAX_SIZE = 10;
        ExecutorService exec = Executors.newFixedThreadPool(MAX_SIZE);

        for (int i = 0; i < 10 * MAX_SIZE; i++) {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(Long.MAX_VALUE);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }

        for (int i = 0; i < 200 && factory.numCreated.get() < MAX_SIZE; i++)
            Thread.sleep(100);
        assertEquals(factory.numCreated.get(), MAX_SIZE);
        exec.shutdownNow();
    }
}
/**
 * 12-8 测试ThreadPoolExecutor的线程工厂类
 */
class TestingThreadFactory implements ThreadFactory {
    public final AtomicInteger numCreated = new AtomicInteger();
    private final ThreadFactory factory = Executors.defaultThreadFactory();

    public Thread newThread(Runnable runnable) {
        numCreated.incrementAndGet();
        return factory.newThread(runnable);
    }
}
