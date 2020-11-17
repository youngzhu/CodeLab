package com.youngzy.book.concurrency.ch05;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * 5-16 使用HashMap和同步机制来初始化缓存
 *
 * 对整个方法进行同步，每次只能有线程访问
 * 可能比不用缓存，直接计算，更慢
 */
public class Memoizer1<A, V> implements Computable<A, V> {
    private final Map<A, V> cache = new HashMap<>();
    private final Computable<A, V> c;

    public Memoizer1(Computable<A, V> c) {
        this.c = c;
    }

    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}

interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}

class ExpensiveFunction implements Computable<String, BigInteger> {
    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        return new BigInteger(arg);
    }
}
