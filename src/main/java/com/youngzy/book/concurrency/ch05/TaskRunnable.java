package com.youngzy.book.concurrency.ch05;

import javafx.concurrent.Task;

import java.util.concurrent.BlockingDeque;

/**
 * 5-10 恢复中断状态以免丢失中断
 *
 * !!! 出现InterruptedException是不应该做的事情是：
 * 捕获它但不做任何处理
 */
public class TaskRunnable implements Runnable {
    BlockingDeque<Task> queue;

    @Override
    public void run() {
        try {
            processTask(queue.take());
        } catch (InterruptedException e) {
            // 恢复被中断的状态
            Thread.currentThread().interrupt();
        }
    }

    void processTask(Task task) {
        // ...
    }

    interface Task{}
}
