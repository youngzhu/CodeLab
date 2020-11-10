package com.youngzy.book.concurrency.ch03;

/**
 * 3-4 数绵羊
 *
 * volatile变量的一种典型用法：检查某个状态标记以判断是否退出循环
 */
public class CountingSheep {
    volatile boolean asleep;

    void tryToSleep() {
        while (! asleep) {
            countSomeSheep();
        }
    }

    private void countSomeSheep() {
        // 1 2 3 ...
    }
}
