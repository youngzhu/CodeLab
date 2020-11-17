package com.youngzy.book.concurrency.ch05;

import java.util.Vector;

/**
 * 5-2 使用客户端加锁的复合操作
 */
public class SafeVectorHelper {
    public static Object getLast(Vector list) {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            return list.get(lastIndex);
        }
    }

    public static void deleteLast(Vector list) {
        synchronized (list) {
            int lastIndex = list.size() - 1;
            list.remove(lastIndex);
        }
    }
}
