package com.youngzy.stackskills.multithreading;

/**
 * 对于 static 的变量会如何 ？
 */
public class SynchronizedProblem2 {
    public static void main(String[] args) {
        Counter2 counter1 = new Counter2();
        Counter2 counter2 = new Counter2();

        // 多个对象
        // 会有问题
        // 结论：synchronized 只对同一个对象实例有作用，不同的实例无效
//        Thread thread1 = new Thread(counter1);
//        Thread thread2 = new Thread(counter2);

        // 如果2个线程共用1个对象
        // 没有问题
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

class Counter2 implements Runnable {
    private static int c;

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
