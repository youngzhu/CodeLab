package com.youngzy.book.concurrency.ch16;

/**
 * 16-6 延长初始化占位类模式
 */
public class ResourceFactory {
    // JVM 将推迟占位类的初始化
    // 直到开始使用这个类时才初始化
    private static class ResourceHolder {
        public static Resource resource = new Resource();
    }

    public static Resource getResource() {
        return ResourceHolder.resource;
    }

    static class Resource {}
}
