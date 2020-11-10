package com.youngzy.book.concurrency.ch03;

/**
 * 3-1 在没有同步的情况下共享变量（不要这么做）
 */
public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            System.out.println("read thread...");
            while (! ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        new ReaderThread().start();
        // 以下2步可能会被重排序，无法预知
        number = 42;
        ready = true;
    }
}
