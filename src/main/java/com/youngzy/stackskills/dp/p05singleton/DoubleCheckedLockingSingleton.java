package com.youngzy.stackskills.dp.p05singleton;

public class DoubleCheckedLockingSingleton {
    /*
    volatile 跟线程无关，不会被缓存
    每次读写都是在内存里
     */
    private volatile static DoubleCheckedLockingSingleton singleton;

    private DoubleCheckedLockingSingleton() {

    }

    public static DoubleCheckedLockingSingleton getInstance() {
        // check 1
        if (singleton == null) {
            synchronized (DoubleCheckedLockingSingleton.class) {
                // check 2
                if (singleton == null) {
                    singleton = new DoubleCheckedLockingSingleton();
                }
            }
        }

        return singleton;
    }
}
