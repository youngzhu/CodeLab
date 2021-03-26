package com.youngzy.book.concurrency.exercise;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class Exercise01Test {

    static final int[] DATA_100 = new int[100];
    static final int[] DATA_1000 = new int[1000];
    static final int[] DATA_10000 = new int[10000];
    static final int[] DATA_100000 = new int[100000];

    Exercise01 checker = new Exercise01();

    @Before
    public void setUp() throws Exception {
        // 100 初始化
        for (int i = 0; i < 100; i ++) {
            DATA_100[i] = i;
        }
        DATA_100[98] = 101;
        DATA_100[95] = 0;

        Random random = new Random();
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
    public void checkDuplication() {
        String result = checker.checkDuplication(DATA_100);
        assertEquals("存在重复数据：[0]", result);

        result = checker.checkDuplication(DATA_1000);
        System.out.println(result);

        result = checker.checkDuplication(DATA_10000);
        System.out.println(result);

        result = checker.checkDuplication(DATA_10000);
        System.out.println(result);
    }

    @Test
    public void checkRange() {
        String result = checker.checkRange(DATA_100, 0, 99);
        assertEquals("[101]超出范围", result);

        result = checker.checkRange(DATA_1000, 0, 999);
        System.out.println(result);

        result = checker.checkRange(DATA_10000, 0, 9999);
        System.out.println(result);

        result = checker.checkRange(DATA_100000, 0, 99999);
        System.out.println(result);
    }
}