package com.youngzy.book.concurrency.ch05;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 5-17 用 ConcurrentHashMap 替换 HashMap
 *
 * ConcurrentHashMap是线程安全的，所以 compute 方法不需要同步了
 *
 * 还存在一个问题：如果某个线程启动来一个开销很大的计算，而其他线程并不知道，那么就会重复
 * 这个计算。
 */
public class Memoizer2<A, V> implements Computable<A, V> {
    private final Map<A, V> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer2(Computable<A, V> c) {
        this.c = c;
    }

    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
