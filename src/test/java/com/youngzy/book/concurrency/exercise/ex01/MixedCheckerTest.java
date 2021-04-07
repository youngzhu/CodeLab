package com.youngzy.book.concurrency.exercise.ex01;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class MixedCheckerTest {
    static final int[] DATA_100 = new int[100];
    static final int[] DATA_500 = new int[500];
    static final int[] DATA_1000 = new int[1000];
    static final int[] DATA_10000 = new int[10000];
    static final int[] DATA_100000 = new int[100000];

    SequentialChecker sequentialChecker;
    ConcurrentCheckerV0 concurrentCheckerV0;
    ConcurrentCheckerV1 concurrentCheckerV1;
    ConcurrentCheckerV2 concurrentCheckerV2;

    @Before
    public void setUp() throws Exception {
        sequentialChecker = new SequentialChecker();
        concurrentCheckerV0 = new ConcurrentCheckerV0();
        concurrentCheckerV1 = new ConcurrentCheckerV1();
        concurrentCheckerV2 = new ConcurrentCheckerV2();

        // 100 初始化
        for (int i = 0; i < 100; i ++) {
            DATA_100[i] = i;
        }
        DATA_100[98] = 101;
        DATA_100[55] = 101;
        DATA_100[5] = 101;

        DATA_100[95] = 0;

        Random random = new Random();

        // 500 初始化
        for (int i = 0; i < 500; i ++) {
            DATA_500[i] = random.nextInt(510);
        }

        // 1000 初始化
        for (int i = 0; i < 1000; i ++) {
            DATA_1000[i] = random.nextInt(1009);
        }

        // 10000 初始化
        for (int i = 0; i < 10000; i ++) {
            DATA_10000[i] = random.nextInt(10009);
        }

        // 100000 初始化
        for (int i = 0; i < 100000; i ++) {
            DATA_100000[i] = random.nextInt(100009);
        }
    }

    /**
     * 并行与串行的比较
     */
    @Test
    public void check() {
        List<String> result1 = sequentialChecker.check(DATA_100, 0, 99);
        List<String> result2 = concurrentCheckerV0.check(DATA_100, 0, 99);
        assertEquals(result2.get(0), result1.get(0));
        assertEquals(result2.get(1), result1.get(1));

        result1 = sequentialChecker.check(DATA_1000, 0, 999);
        result2 = concurrentCheckerV0.check(DATA_1000, 0, 999);
        assertEquals(result2.get(0), result1.get(0));
        assertEquals(result2.get(1), result1.get(1));

        result1 = sequentialChecker.check(DATA_10000, 0, 9999);
        result2 = concurrentCheckerV0.check(DATA_10000, 0, 9999);
        assertEquals(result2.get(0), result1.get(0));
        assertEquals(result2.get(1), result1.get(1));

        result1 = sequentialChecker.check(DATA_100000, 0, 99999);
        result2 = concurrentCheckerV0.check(DATA_100000, 0, 99999);
        assertEquals(result2.get(0), result1.get(0));
        assertEquals(result2.get(1), result1.get(1));
    }

    /**
     * V0 V1 的比较
     * 一个线程一条数据 VS 一个线程多条数据
     */
    @Test
    public void check0VS1() {
        List<String> result1 = concurrentCheckerV0.check(DATA_100, 0, 99);
        List<String> result2 = concurrentCheckerV1.check(DATA_100, 0, 99);
        assertEquals(result2.get(0), result1.get(0));
        // 因为无序，简单地比较一下长度
        assertEquals(result2.get(1).length(), result1.get(1).length());


        result1 = concurrentCheckerV0.check(DATA_500, 0, 500);
        result2 = concurrentCheckerV1.check(DATA_500, 0, 500);
        assertEquals(result2.get(0), result1.get(0));
        assertEquals(result2.get(1).length(), result1.get(1).length());

        result1 = concurrentCheckerV0.check(DATA_1000, 0, 999);
        result2 = concurrentCheckerV1.check(DATA_1000, 0, 999);
        assertEquals(result2.get(0), result1.get(0));
        assertEquals(result2.get(1).length(), result1.get(1).length());

        result1 = concurrentCheckerV0.check(DATA_10000, 0, 9999);
        result2 = concurrentCheckerV1.check(DATA_10000, 0, 9999);
        assertEquals(result2.get(0), result1.get(0));
        assertEquals(result2.get(1).length(), result1.get(1).length());
    }

    /**
     * 并行与串行的比较
     */
    @Test
    public void check2() {
        List<String> result1 = sequentialChecker.check(DATA_100, 0, 99);
        List<String> result2 = concurrentCheckerV0.check(DATA_100, 0, 99);
        List<String> result3 = concurrentCheckerV2.check(DATA_100, 0, 99);
        assertEquals(result2.get(0), result1.get(0));
        assertEquals(result2.get(1), result1.get(1));
        assertEquals(result3.get(0), result1.get(0));
        assertEquals(result3.get(1), result1.get(1));

        result1 = sequentialChecker.check(DATA_1000, 0, 999);
        result2 = concurrentCheckerV0.check(DATA_1000, 0, 999);
        result3 = concurrentCheckerV2.check(DATA_1000, 0, 999);
        assertEquals(result2.get(0), result1.get(0));
        assertEquals(result2.get(1), result1.get(1));
        assertEquals(result3.get(0), result1.get(0));
        assertEquals(result3.get(1), result1.get(1));

        result1 = sequentialChecker.check(DATA_10000, 0, 9999);
        result2 = concurrentCheckerV0.check(DATA_10000, 0, 9999);
        result3 = concurrentCheckerV2.check(DATA_10000, 0, 9999);
        assertEquals(result2.get(0), result1.get(0));
        assertEquals(result2.get(1), result1.get(1));
        assertEquals(result3.get(0), result1.get(0));
        assertEquals(result3.get(1), result1.get(1));

        result1 = sequentialChecker.check(DATA_100000, 0, 99999);
        result2 = concurrentCheckerV0.check(DATA_100000, 0, 99999);
        result3 = concurrentCheckerV2.check(DATA_100000, 0, 99999);
        assertEquals(result2.get(0), result1.get(0));
        assertEquals(result2.get(1), result1.get(1));
        assertEquals(result3.get(0), result1.get(0));
        assertEquals(result3.get(1), result1.get(1));
    }
}