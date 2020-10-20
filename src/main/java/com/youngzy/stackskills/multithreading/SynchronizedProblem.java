package com.youngzy.stackskills.multithreading;

import java.util.List;

public class SynchronizedProblem {
    public static void main(String[] args) {
        Counter counter1 = new Counter();
        Counter counter2 = new Counter();

        // 各用各的，没有问题
//        Thread thread1 = new Thread(counter1);
//        Thread thread2 = new Thread(counter2);

        // 如果2个线程共用1个对象
        // 会发现 部分数字被使用了2次
        // 在 run 方法上加 synchronized 可以解决
        Thread thread1 = new Thread(counter1);
        Thread thread2 = new Thread(counter1);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class Counter implements Runnable {
    private int c;

    @Override
    public synchronized void run() {
        while (c <= 100) {
            System.out.println(c ++);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }

    }
}
