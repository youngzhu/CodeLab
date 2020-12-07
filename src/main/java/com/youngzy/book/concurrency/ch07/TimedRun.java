package com.youngzy.book.concurrency.ch07;

import com.youngzy.book.concurrency.ch05.LaunderThrowable;

import java.util.concurrent.*;

/**
 * 7-10 通过 Future 来取消任务
 */
public class TimedRun {
    private static final ExecutorService taskExec = Executors.newCachedThreadPool();

    public static void timedRun(Runnable r,
                                long timeout, TimeUnit unit) throws InterruptedException {
        Future<?>  task = taskExec.submit(r);
        try {
            task.get(timeout, unit);
        } catch (TimeoutException e) {
            // 任务将在 finally 中取消
        } catch (ExecutionException e) {
            // 如果有异常，则重新抛出
            throw LaunderThrowable.launderThrowable(e.getCause());
        } finally {
            // ！！！良好的编程习惯：取消那些不再需要结果的任务
            // 如果任务已经结束，执行取消不会带来任何影响
            // 如果任务正在运行，将被中断
            task.cancel(true);
        }

    }
}
