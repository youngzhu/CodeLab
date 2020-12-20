package com.youngzy.book.concurrency.ch09;

import java.util.concurrent.*;

/**
 * 9-7 支持取消，完成通知以及进度通知的后台任务类
 *
 * @param <V>
 */
public abstract class BackgroundTask<V> implements Runnable, Future<V> {
    private final FutureTask<V> computation = new Computation();

    private class Computation extends FutureTask<V> {
        public Computation() {
            super(new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return BackgroundTask.this.compute();
                }
            });
        }

        @Override
        protected void done() {
            GuiExecutor.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    V value = null;
                    Throwable throwable = null;
                    boolean cancelled = false;
                    try {
                        value = get();
                    } catch (ExecutionException e) {
                        throwable = e.getCause();
                    } catch (CancellationException e) {
                        cancelled = true;
                    } catch (InterruptedException consumed) {

                    } finally {
                        onCompletion(value, throwable, cancelled);
                    }
                }
            });
        }
    }

    protected void setProgress(final int current, final int max) {
        GuiExecutor.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                onProgress(current, max);
            }
        });
    }

    // 在后台线程中被取消
    protected abstract V compute() throws Exception;

    // 在事件线程中被取消
    protected void onCompletion(V result, Throwable throwable, boolean cancelled) {

    }

    protected void onProgress(int current, int max) {

    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return computation.cancel(mayInterruptIfRunning);
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        return computation.get();
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return computation.get(timeout, unit);
    }

    @Override
    public boolean isCancelled() {
        return computation.isCancelled();
    }

    @Override
    public boolean isDone() {
        return computation.isDone();
    }

    @Override
    public void run() {
        computation.run();
    }
}
