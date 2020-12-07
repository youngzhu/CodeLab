package com.youngzy.book.concurrency.ch07;

import java.util.*;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 7-21 在ExecutorService中跟踪关闭之后被取消的任务
 */
public class TrackingExecutor extends AbstractExecutorService {
    private final ExecutorService executorService;
    private final Set<Runnable> tasksCancelledAtShutdown =
            Collections.synchronizedSet(new HashSet<>());

    public TrackingExecutor(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void shutdown() {
        executorService.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return executorService.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return executorService.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return executorService.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return executorService.awaitTermination(timeout, unit);
    }

    public List<Runnable> getCancelledTasks() {
        if (! executorService.isTerminated())
            throw new IllegalStateException("xx");
        return new ArrayList<>(tasksCancelledAtShutdown);
    }

    public void execute(final Runnable runnable) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    runnable.run();
                } finally {
                    if (isShutdown() && Thread.currentThread().isInterrupted()) {
                        tasksCancelledAtShutdown.add(runnable);
                    }
                }
            }
        });
    }
}
