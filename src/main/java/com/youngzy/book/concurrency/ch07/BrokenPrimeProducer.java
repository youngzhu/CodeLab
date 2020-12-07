package com.youngzy.book.concurrency.ch07;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * 7-3 不可靠的取消操作将把生产者置于阻塞的操作中（不要这么做）
 */
public class BrokenPrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;
    private volatile boolean cancelled = false;

    BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        try {

            while (! cancelled) {
                queue.put(p = p.nextProbablePrime());
            }
        } catch (InterruptedException consumed) {

        }
    }

    public void cancel() {
        cancelled = true;
    }

    void consumePrimes() throws InterruptedException {
        BlockingQueue<BigInteger> primes = null;
        BrokenPrimeProducer producer = new BrokenPrimeProducer(primes);

        producer.start();
        try {
            while (needMore()) {
                consume(primes.take());
            }
        } finally {
            producer.cancel();
        }
    }

    private void consume(BigInteger prime) {
    }

    private boolean needMore() {
        return true;
    }
}
