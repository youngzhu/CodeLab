package com.youngzy.keepinmind.sort;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class SortTest {
    final static int n = 1000;// 数据量
    static int[] SEED = new int[n]; // 整个类共享，保证所有排序方法的入参都一样
    static int[] EXPECTED; // 对 SEED 排序后的数组。使用工具类

    ISort sorter;

    int[] before, after;

    @BeforeAll
    public static void beforeClass() throws Exception {
        Random rdm = new Random();
        for (int i = 0; i < n; i++) {
            SEED[i] = rdm.nextInt(n * n);
        }
        EXPECTED = Arrays.copyOf(SEED, n);
        Arrays.sort(EXPECTED);
    }

    @BeforeEach
    public void setUp() throws Exception {
        before = Arrays.copyOf(SEED, n);
        System.out.println(Arrays.toString(before));
        System.out.println(Arrays.toString(EXPECTED));
    }

    @Test
    public void insertSort() {
        sorter = new InsertSort();
        test();
    }

    @Test
    public void bubbleSort() {
        sorter = new BubbleSort();
        test();
    }

    @Test
    public void compareCountSort() {
        sorter = new CompareCountSort();
        test();
    }

    private void test() {
        long begin = System.currentTimeMillis();
        sorter.sort(before);
        long end = System.currentTimeMillis();
        System.out.println((end - begin) + "ms");
        assertArrayEquals(EXPECTED, before);
    }
}