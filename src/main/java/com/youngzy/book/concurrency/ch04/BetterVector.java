package com.youngzy.book.concurrency.ch04;

import java.util.Vector;

/**
 * 4-13 扩展Vector类，添加一个"若没有则添加"的方法
 * @param <E>
 */
public class BetterVector<E> extends Vector<E> {
    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !contains(x);
        if (absent) {
            add(x);
        }
        return absent;
    }
}
