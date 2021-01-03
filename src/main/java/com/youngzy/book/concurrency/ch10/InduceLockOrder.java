package com.youngzy.book.concurrency.ch10;

/**
 * 10-3 通过锁顺序来避免死锁
 */
public class InduceLockOrder {

    // 加时赛锁（TieBreaking）
    // 在获得两个Account锁之前，首先获得加时赛锁，
    // 从而保证每次只有一个线程以未知的顺序来获得这两个锁，
    // 从而消除了死锁发生的可能性
    private static final Object tieLock = new Object();

    public void transferMoney(final Account from,
                              final Account to,
                              final DollarAmount amount)
            throws InsufficientFundsException {
        class Helper {
            public void transfer() throws InsufficientFundsException {
                if (from.getBalance().compareTo(amount) < 0) {
                    throw new InsufficientFundsException();
                } else {
                    from.debit(amount);
                    to.credit(amount);
                }
            }
        }

        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);

        if (fromHash < toHash) {
            synchronized (from) {
                synchronized (to) {
                    new Helper().transfer();
                }
            }
        } else if (fromHash > toHash) {
            synchronized (to) {
                synchronized (from) {
                    new Helper().transfer();
                }
            }
        } else {
            synchronized (tieLock) {
                synchronized (from) {
                    synchronized (to) {
                        new Helper().transfer();
                    }
                }
            }
        }
    }

    interface DollarAmount extends Comparable<DollarAmount> {}

    interface Account {
        void debit(DollarAmount amount);
        void credit(DollarAmount amount);
        DollarAmount getBalance();
        int getAcctNo();
    }

    class InsufficientFundsException extends Exception {}
}
