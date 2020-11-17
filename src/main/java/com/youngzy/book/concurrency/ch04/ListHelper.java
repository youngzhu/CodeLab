package com.youngzy.book.concurrency.ch04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 4-14 非线程安全的"若没有则添加"（不要这么做）
 *
 * 看似方法已经同步
 * 但无论list使用的哪个锁来同步，这个锁跟helper上的锁肯定不是同一个
 */
class BadListHelper<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<>());

    public synchronized boolean putIfAbsent(E x) {
        boolean absent = ! list.contains(x);
        if (absent) {
            list.add(x);
        }
        return absent;
    }
}

/**
 * 4-15 通过客户端加锁来实现"若没有则添加"
 */
class GoodListHelper<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<>());

    public boolean putIfAbsent(E x) {
        synchronized (list) {
            boolean absent = ! list.contains(x);
            if (absent) {
                list.add(x);
            }
            return absent;
        }
    }
}