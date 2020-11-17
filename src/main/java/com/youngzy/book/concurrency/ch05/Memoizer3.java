package com.youngzy.book.concurrency.ch05;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 5-18 基于FutureTask
 *
 * Memoizer3 首先检查某个相应的计算是否已经开始
 * Memoizer2 刚好相反，它首先判断某个计算是否已经完成
 *
 * Memoizer2的问题，在3中仍然存在，只是概率要小很多
 * 因为 compute 方法中的if是非原子性的
 *
 */
public class Memoizer3<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    public V compute(A arg) throws InterruptedException {
        Future<V> result = cache.get(arg);
        if (result == null) {
            Callable<V> eval = new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return c.compute(arg);
                }
            };

            FutureTask<V> futureTask = new FutureTask<>(eval);
            result = futureTask;
            cache.put(arg, result);
            // 在这里将调用 c.compute()
            futureTask.run();
        }
        try {
            return result.get();
        } catch (ExecutionException e) {
            throw LaunderThrowable.launderThrowable(e.getCause());
        }
    }
}
