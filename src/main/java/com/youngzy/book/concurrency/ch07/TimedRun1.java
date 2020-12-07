package com.youngzy.book.concurrency.ch07;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 7-8 在外部线程中安排中断（不要这么做）
 */
public class TimedRun1 {
    private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(1);

    public static void timedRun(Runnable r, long timeout, TimeUnit unit) {
        final Thread task = Thread.currentThread();
        cancelExec.schedule(new Runnable() {
            @Override
            public void run() {
                task.interrupt();
            }
        }, timeout, unit);

        r.run();
    }
}
