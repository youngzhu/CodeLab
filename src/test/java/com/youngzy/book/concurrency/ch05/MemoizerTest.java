package com.youngzy.book.concurrency.ch05;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MemoizerTest {

    // 随机数的最大值和最小值
    private static final int RANDOM_MAX = 1000;
    private static final int RANDOM_MIN = 0;
    // 计算次数
    private static final int COMPUTE_TIMES = 1000;
    // CPU 数量，决定线程数
    private static final int NUM_OF_CPU = Runtime.getRuntime().availableProcessors();

    static int counter = 0;

    static RandomDataGenerator random = new RandomDataGenerator();
    Computable<String, BigInteger> function;

    /**
     * 缓存3
     * 0-1000次：306901
     * 1-1000次：298388,298611
     * 2-1000次：190839
     *
     * 提高命中率20%（0-1000 改为 0-800）：177648
     * 提高命中率50%（0-1000 改为 0-500）：132321
     *
     * 提高命中率对test2没啥影响：298971
     *
     * 3-1000次（0-500）：132553
     * 3-1000次（0-1000）：191411
     *
     * 3跟2比差不多
     *
     * 4-1000次（0-500）：125207
     * 4-1000次（0-1000）：189820
     *
     * 4跟3比有进步
     *
     * 5-1000次（0-500）：131282
     * 5-1000次（0-1000）：187411，188781
     */
    @Test
    public void test5() throws InterruptedException {
        long start = System.currentTimeMillis();

        function = new MemoizerFinal<>(new ExpensiveFunction());

        startThread();

        long end = System.currentTimeMillis();
        System.out.println("test5-缓存Final：" + (end - start));
    }

    /**
     * 缓存3
     * 0-1000次：306901
     * 1-1000次：298388,298611
     * 2-1000次：190839
     *
     * 提高命中率20%（0-1000 改为 0-800）：177648
     * 提高命中率50%（0-1000 改为 0-500）：132321
     *
     * 提高命中率对test2没啥影响：298971
     *
     * 3-1000次（0-500）：132553
     * 3-1000次（0-1000）：191411
     *
     * 3跟2比差不多
     *
     * 4-1000次（0-500）：125207
     * 4-1000次（0-1000）：189820
     *
     * 4跟3比有进步
     */
    @Test
    public void test4() throws InterruptedException {
        long start = System.currentTimeMillis();

        function = new Memoizer3<>(new ExpensiveFunction());

        startThread();

        long end = System.currentTimeMillis();
        System.out.println("test4-缓存3：" + (end - start));
    }


    /**
     * 缓存2
     * 0-1000次：306901
     * 1-1000次：298388,298611
     * 2-1000次：190839
     *
     * 提高命中率20%（0-1000 改为 0-800）：177648
     * 提高命中率50%（0-1000 改为 0-500）：132321
     *
     * 提高命中率对test2没啥影响：298971
     *
     * 3-1000次（0-500）：132553
     * 3-1000次（0-1000）：191411
     *
     * 3跟2比差不多
     */
    @Test
    public void test3() throws InterruptedException {
        long start = System.currentTimeMillis();

        function = new Memoizer2<>(new ExpensiveFunction());

        startThread();

        long end = System.currentTimeMillis();
        System.out.println("test3-缓存2：" + (end - start));
    }

    /**
     * 缓存1
     * 0-1000次：306901
     * 1-1000次：298388,298611
     * 2-1000次：190839
     *
     * 提高命中率20%（0-1000 改为 0-800）：177648
     * 提高命中率50%（0-1000 改为 0-500）：132321
     *
     * 提高命中率对test2没啥影响：298971
     */
    @Test
    public void test2() throws InterruptedException {
        long start = System.currentTimeMillis();

        function = new Memoizer1<>(new ExpensiveFunction());

        startThread();

        long end = System.currentTimeMillis();
        System.out.println("test2-缓存1：" + (end - start));
    }

    private void startThread() throws InterruptedException {
        for (int i = 0; i < NUM_OF_CPU; i++) {
            Runnable compute = new Runnable() {
                @Override
                public void run() {
                    while (counter <= COMPUTE_TIMES) {
                        counter++;
                        try {
                            test();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            };

            Thread thread = new Thread(compute);
            thread.start();
            // 要join，不然主线程就不管它了
            thread.join();
        }
    }

    @Test
    public void cpus() {
        System.out.println("CPUs:" + NUM_OF_CPU);
    }

    /**
     * 常规的多线程
     *
     * 0-1000次：306901
     * 1-1000次：298388,298611
     *
     * 跟0比，感觉没啥进步啊
     */
    @Test
    public void test1() throws InterruptedException {
        function = new ExpensiveFunction();

        long start = System.currentTimeMillis();

        startThread();

        long end = System.currentTimeMillis();

        System.out.println("test1-并行：" + (end - start));
    }

    /**
     * 串行
     * 0-1000次：306901
     */
    @Test
    public void test0() throws InterruptedException {
        function = new ExpensiveFunction();

        long start = System.currentTimeMillis();

        for (int i = 0; i < COMPUTE_TIMES; i++) {
            test();
        }
        long end = System.currentTimeMillis();

        System.out.println("test0-串行：" + (end - start));
    }

    private void test() throws InterruptedException {
        int randomVal = random.nextInt(RANDOM_MIN, RANDOM_MAX);
        String arg = String.valueOf(randomVal);

        BigInteger excepted = new BigInteger(arg);
        BigInteger actual = function.compute(arg);

//        System.out.println(excepted + "==" + actual);
        assertEquals(excepted, actual);
    }
}