package com.youngzy.book.concurrency.ch13;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * 13-3 通过tryLock来避免锁顺序死锁
 *
 * 使用tryLock来获取两个锁，如果不能同时获得，那么就回退并重新尝试
 * 在休眠时间中包括固定部分和随机部分，从而降低发生活锁的可能性
 * 如果在指定时间内不能完成，则返回一个失败状态，从而使操作平缓的失败
 */
public class DeadlockAvoidance {
    private static Random random = new Random();

    public boolean transferMoney(Account fromAcct,
                                 Account toAcct,
                                 DollarAmount amount,
                                 long timeout,
                                 TimeUnit unit)
        throws InsufficientFundsException, InterruptedException {
        long fixedDelay = getFixedDelayComponentNanos(timeout, unit);
        long randMod = getRandomDelayModulusNanos(timeout, unit);
        long stopTime = System.nanoTime() + unit.toNanos(timeout);

        while (true) {
            if (fromAcct.lock.tryLock()) {
                try {
                    if (toAcct.lock.tryLock()) {
                        try {
                            if (fromAcct.getBalance().compareTo(amount) < 0)
                                throw new InsufficientFundsException();
                            else {
                                fromAcct.debit(amount);
                                toAcct.credit(amount);
                                return true;
                            }
                        } finally {
                            toAcct.lock.unlock();
                        }
                    }
                } finally {
                    fromAcct.lock.unlock();
                }
            }

            if (System.nanoTime() < stopTime)
                return false;
            NANOSECONDS.sleep(fixedDelay + random.nextLong() % randMod);
        }
    }


    private static final int DELAY_FIXED = 1;
    private static final int DELAY_RANDOM = 2;

    private long getFixedDelayComponentNanos(long timeout, TimeUnit unit) {
        return DELAY_FIXED;
    }

    private long getRandomDelayModulusNanos(long timeout, TimeUnit unit) {
        return DELAY_RANDOM;
    }

    class DollarAmount implements Comparable<DollarAmount> {
        @Override
        public int compareTo(DollarAmount o) {
            return 0;
        }

        DollarAmount(int dollars) {

        }
    }

    class Account {
        public Lock lock;

        void debit(DollarAmount dollarAmount) {

        }

        void credit(DollarAmount amount) {

        }

        DollarAmount getBalance() {
            return null;
        }
    }

    class InsufficientFundsException extends Exception {

    }
}
