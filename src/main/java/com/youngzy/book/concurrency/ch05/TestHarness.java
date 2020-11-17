package com.youngzy.book.concurrency.ch05;

import java.util.concurrent.CountDownLatch;

/**
 * 5-11 在计时测试中使用CountDownLatch来启动和停止线程
 *
 * 有两个闭锁：startGate 和 endGate
 * startGate初始值为1，endGate初始值为工作线程的数量
 * 每个线程首先要做的事就是在启动门上等待，从而确保所有线程都就绪后才开始执行
 * 而每个线程要做的最后一件事是调用结束门的 countDown 方法减1，
 * 这能使主线程高效地等待直到所有工作线程都执行完成
 *
 * 为什么要使用闭锁，而不是在线程创建后就立即启动？
 * 或许，我们想要测试n个线程并发执行某个任务时需要的时间。
 * 如果在创建线程后立即启动它们，那么先启动的线程将领先于后启动的，并且活跃线程数量会随着
 * 时间的推移而增加或减少
 *
 * 启动门将使主线程能够同时释放所有的工作线程
 * 结束门则使主线程能够等待最后一个线程执行完成，而不是顺序地等待每个线程执行完成
 */
public class TestHarness {
    public long timeTasks(int numThread, final Runnable task)
        throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(numThread);

        for (int i = 0; i < numThread; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException ignored) {

                    }
                }
            };

            t.start();
        }

        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }
}
