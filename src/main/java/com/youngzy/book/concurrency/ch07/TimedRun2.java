package com.youngzy.book.concurrency.ch07;

import com.youngzy.book.concurrency.ch05.LaunderThrowable;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newScheduledThreadPool;


/**
 * 7-9 在专门的线程中中断
 */
public class TimedRun2 {
    private static final ScheduledExecutorService cancelExec = newScheduledThreadPool(1);

    public static void timedRun(final Runnable r,
                                long timeout, TimeUnit unit) throws InterruptedException {
        class RethrowableTask implements Runnable {
            private volatile Throwable t;

            @Override
            public void run() {
                try {
                    r.run();
                } catch (Throwable throwable) {
                    this.t = throwable;
                }
            }

            void rethrow() {
                if (t != null) {
                    throw LaunderThrowable.launderThrowable(t);
                }
            }
        }

        RethrowableTask task = new RethrowableTask();
        final Thread taskThread = new Thread(task);
        taskThread.start();
        cancelExec.schedule(new Runnable() {
            @Override
            public void run() {
                taskThread.interrupt();
            }
        }, timeout, unit);

        taskThread.join(unit.toMillis(timeout));
        task.rethrow();

    }
}


