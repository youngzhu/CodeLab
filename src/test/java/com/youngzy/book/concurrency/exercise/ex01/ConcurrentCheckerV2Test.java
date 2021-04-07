package com.youngzy.book.concurrency.exercise.ex01;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class ConcurrentCheckerV2Test {
    static final int[] DATA_100 = new int[100];
    static final int[] DATA_500 = new int[500];
    static final int[] DATA_1000 = new int[1000];
    static final int[] DATA_10000 = new int[10000];
    static final int[] DATA_100000 = new int[100000];

    ConcurrentCheckerV2 checker;

    @Before
    public void setUp() throws Exception {
        checker = new ConcurrentCheckerV2();

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
            DATA_500[i] = random.nextInt(509);
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

    @Test
    public void check() {
        List<String> result = checker.check(DATA_100, 0, 99);
//        System.out.println(result);
//        assertEquals("存在重复数据：[101, 0]", result.get(0));
//        assertEquals("[101]超出范围", result.get(1));

        result = checker.check(DATA_500, 0, 500);

        result = checker.check(DATA_1000, 0, 999);

        result = checker.check(DATA_10000, 0, 9999);

//        result = checker.check(DATA_100000, 0, 99999);
    }
}