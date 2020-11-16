package com.youngzy.book.concurrency.ch05;

import java.util.Vector;

/**
 * 5-1 可能导致错误的复合操作
 */
public class UnsafeVectorHelper {
    public static Object getLast(Vector list) {
        int lastIndex = list.size() - 1;
        return list.get(lastIndex);
    }

    public static void deleteLast(Vector list) {
        int lastIndex = list.size() - 1;
        list.remove(lastIndex);
    }
}
