package com.youngzy.book.concurrency.ch05;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 5-19 Memoizer 最终版本
 *
 * 使用 ConcurrentMap 中的原子方法 putIfAbsent
 *
 */
public class MemoizerFinal<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public MemoizerFinal(Computable<A, V> c) {
        this.c = c;
    }

    public V compute(final A arg) throws InterruptedException {
        while (true) {
            // 没看明白，为什么要 循环
            Future<V> result = cache.get(arg);
            if (result == null) {
                Callable<V> eval = new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return c.compute(arg);
                    }
                };

                FutureTask<V> futureTask = new FutureTask<>(eval);
                result = cache.putIfAbsent(arg, futureTask);
                if (result == null) {
                    result = futureTask;
                    futureTask.run();
                }
            }
            try {
                return result.get();
            } catch (ExecutionException e) {
                throw LaunderThrowable.launderThrowable(e.getCause());
            }
        }
    }
}
