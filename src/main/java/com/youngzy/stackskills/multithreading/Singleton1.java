package com.youngzy.stackskills.multithreading;

/**
 * 最基本的单例模式
 *
 * 缺点：synchronized 方法影响性能
 */
public class Singleton1 {
    private static Singleton1 singleton;

    private Singleton1(){}

    public static synchronized Singleton1 getInstance() {
        if (null == singleton) {
            singleton = new Singleton1();
        }
        return singleton;
    }
}
