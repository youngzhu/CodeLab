package com.youngzy.book.concurrency.ch10;

import java.util.Random;

/**
 * 10-4 在典型条件下会发生死锁的循环
 */
public class DemonstrateDeadlock {
    private static final int NUM_THREADS = 20;
    private static final int NUM_ACCOUNTS = 5;
    private static final int NUM_ITERATIONS = 1000000;

    public static void main(String[] args) {
        final Random random = new Random();
        final DynamicOrderDeadlock.Account[] accounts = new DynamicOrderDeadlock.Account[NUM_ACCOUNTS];

        for (int i = 0; i < accounts.length; i++)
            accounts[i] = new DynamicOrderDeadlock.Account();

        class TransferThread extends Thread {
            @Override
            public void run() {
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    int from = random.nextInt(NUM_ACCOUNTS);
                    int to = random.nextInt(NUM_ACCOUNTS);
                    DynamicOrderDeadlock.DollarAmount amount = new DynamicOrderDeadlock.DollarAmount(random.nextInt(1000));
                    try {
                        DynamicOrderDeadlock.transferMoney(accounts[from],
                                accounts[to],
                                amount);
                    } catch (DynamicOrderDeadlock.InsufficientFundsException ignored) {

                    }
                }
            }
        }

        for (int i = 0; i < NUM_THREADS; i++)
            new TransferThread().start();
    }
}
