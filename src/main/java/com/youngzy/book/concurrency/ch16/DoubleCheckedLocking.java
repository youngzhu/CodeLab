package com.youngzy.book.concurrency.ch16;

/**
 * 16-7 双重检查加锁（非线程安全）
 *
 * 问题在于：线程可能得到一个仅被部分构造的resource
 */
public class DoubleCheckedLocking {
    private static Resource resource;

    public static Resource getResource() {
        if (resource == null) {
            synchronized (DoubleCheckedLocking.class) {
                if (resource == null)
                    resource = new Resource();
            }
        }
        return resource;
    }

    static class Resource {}
}
