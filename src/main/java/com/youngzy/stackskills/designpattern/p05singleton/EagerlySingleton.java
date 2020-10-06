package com.youngzy.stackskills.designpattern.p05singleton;

public class EagerlySingleton {
    /*
    volatile 跟线程无关，不会被缓存
    每次读写都是在内存里
     */
    private volatile static EagerlySingleton singleton = new EagerlySingleton();

    private EagerlySingleton() {
    }

    public static EagerlySingleton getInstance() {
        return singleton;
    }
}
