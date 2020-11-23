package com.youngzy.leetcode.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 1115. 交替打印FooBar
 *
 * 我们提供一个类：
 *
 * class FooBar {
 *   public void foo() {
 *     for (int i = 0; i < n; i++) {
 *       print("foo");
 *     }
 *   }
 *
 *   public void bar() {
 *     for (int i = 0; i < n; i++) {
 *       print("bar");
 *     }
 *   }
 * }
 * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
 *
 * 请设计修改程序，以确保 "foobar" 被输出 n 次。
 *
 * 示例 1:
 * 输入: n = 1
 * 输出: "foobar"
 * 解释: 这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
 *
 * 示例 2:
 * 输入: n = 2
 * 输出: "foobarfoobar"
 * 解释: "foobar" 将被输出两次。
 *
 * 链接：https://leetcode-cn.com/problems/print-foobar-alternately
 */
public class FooBar {
    private int n;

    private final CyclicBarrier barrier = new CyclicBarrier(2); // 每一组2个动作
    private boolean isDone = true;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            // 一组没结束，等待
            while (! isDone);

            // 新的一轮开始
            isDone = false;

            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            try {
                barrier.await();
            } catch (BrokenBarrierException e) {
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            try {
                barrier.await();
            } catch (BrokenBarrierException e) {
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            isDone = true;
        }
    }
}
