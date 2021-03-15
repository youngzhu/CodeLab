package com.youngzy.book.concurrency.ch11;

import java.util.concurrent.BlockingQueue;

/**
 * 11-1 对任务队列的串行访问
 */
public class WorkerThread extends Thread {
    private final BlockingQueue<Runnable> queue;

    public WorkerThread(BlockingQueue<Runnable> queue) {
        this.queue = queue;
    }

    public void run() {
        while (true) {
            try {
                Runnable task = queue.take();
                task.run();
            } catch (InterruptedException e) {
                break; // 允许线程退出
            }
        }
    }
}
