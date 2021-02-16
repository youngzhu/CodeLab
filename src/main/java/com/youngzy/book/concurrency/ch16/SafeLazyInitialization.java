package com.youngzy.book.concurrency.ch16;

/**
 * 16-4 线程安全的延迟初始化
 */
public class SafeLazyInitialization {
    private static Resource resource;

    public synchronized static Resource getInstance() {
        if (resource == null) {
            resource = new Resource();
        }
        return resource;
    }

    static class Resource {}
}
