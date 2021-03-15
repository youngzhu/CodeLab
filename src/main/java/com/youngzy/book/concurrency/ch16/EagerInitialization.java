package com.youngzy.book.concurrency.ch16;

/**
 * 16-5 提前初始化
 */
public class EagerInitialization {
    private static Resource resource = new Resource();

    public static Resource getInstance() {
        return resource;
    }

    static class Resource {}
}
