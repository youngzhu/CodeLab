package com.youngzy.book.concurrency.ch07;

import javafx.concurrent.Task;

import java.util.concurrent.BlockingQueue;

/**
 * 7-7 不可取消的任务在退出前恢复中断
 */
public class NoncancelableTask {
    public Task getNextTask(BlockingQueue<Task> queue) {
        boolean interrupted = false;

        try {
            while (true) {
                return queue.take();
            }
        } catch (InterruptedException e) {
            // 捕获中断时，先在本地保存
            interrupted = true;
            // 重新尝试
        } finally {
            // 在返回前 恢复中断
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }

        return null;
    }

    interface Task{}
}
