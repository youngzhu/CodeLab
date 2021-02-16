package com.youngzy.book.concurrency.ch15;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 15-4 基于ReentrantLock实现的随机数生成器
 *
 * 未知的父类，15-5 一样
 */
public class ReentrantLockPseudoRandom {
    private final Lock lock = new ReentrantLock(false);
    private int seed;

    ReentrantLockPseudoRandom(int seed) {
        this.seed = seed;
    }

//    public int nextInt(int n) {
//        lock.lock();
//        try {
//            int s = seed;
//            seed = c
//        }
//    }
}
