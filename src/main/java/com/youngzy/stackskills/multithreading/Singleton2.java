package com.youngzy.stackskills.multithreading;

/**
 * 缺点：在初期加载时就初始化了，不管是否会用到这个类
 * 对于消耗较大的实例，不合适
 * 应该用到了再去初始化
 */
public class Singleton2 {
    private static volatile Singleton2 singleton = new Singleton2();

    private Singleton2(){}

    public static Singleton2 getInstance() {
        return singleton;
    }
}
