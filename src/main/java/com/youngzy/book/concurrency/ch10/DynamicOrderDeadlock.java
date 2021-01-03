package com.youngzy.book.concurrency.ch10;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 10-2 动态的锁顺序死锁
 *
 * 看似无害
 * 但如果有两个线程同时调用transferMoney方法，
 * 一个从X向Y转账，一个从Y向X转账，就好发生死锁。
 */
public class DynamicOrderDeadlock {
    // 注意：容易发生死锁
    public static void transferMoney(Account from, Account to,
                                     DollarAmount amount) throws InsufficientFundsException {
        synchronized (from) {
            synchronized (to) {
                if (from.getBalance().compareTo(amount) < 0) {
                    // 余额不足
                    throw new InsufficientFundsException();
                } else {
                    from.debit(amount);
                    to.credit(amount);
                }
            }
        }
    }

    // 未实现
    static class DollarAmount implements Comparable<DollarAmount> {
        public DollarAmount(int amount) {
        }

        public DollarAmount add(DollarAmount amount) {
            return null;
        }

        public DollarAmount subtract(DollarAmount amount) {
            return null;
        }

        @Override
        public int compareTo(DollarAmount o) {
            return 0;
        }
    }

    static class Account {
        private DollarAmount balance;
        private final int acctNo;
        private static final AtomicInteger sequence = new AtomicInteger();

        public Account() {
            acctNo = sequence.incrementAndGet();
        }

        void debit(DollarAmount amount) {
            balance = balance.subtract(amount);
        }

        void credit(DollarAmount amount) {
            balance = balance.add(amount);
        }

        DollarAmount getBalance() {
            return balance;
        }

        int getAcctNo() {
            return acctNo;
        }
    }

    static class InsufficientFundsException extends Exception {}
}
