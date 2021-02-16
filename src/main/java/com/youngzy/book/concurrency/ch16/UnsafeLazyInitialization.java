package com.youngzy.book.concurrency.ch16;

/**
 * 16-3 不安全的延迟初始化
 */
public class UnsafeLazyInitialization {
    private static Resource resource;

    public static Resource getInstance() {
        if (resource == null) {
            resource = new Resource(); // 不安全的发布
        }
        return resource;
    }

    static class Resource {}
}
