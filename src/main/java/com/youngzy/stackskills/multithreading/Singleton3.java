package com.youngzy.stackskills.multithreading;

/**
 * double check
 * 在需要的时候去创建对象
 * 并且 在当前实例对象还为空时再去加锁
 */
public class Singleton3 {
    // volatile 使得变量不会被缓存或缓存，直接操作主内存
    private static volatile Singleton3 singleton;

    private Singleton3(){}

    public static Singleton3 getInstance() {
        // check 1
        if (singleton == null) {
            synchronized (Singleton3.class) {
                // check 2
                if (singleton == null) {
                    singleton = new Singleton3();
                }
            }
        }
        return singleton;
    }
}
