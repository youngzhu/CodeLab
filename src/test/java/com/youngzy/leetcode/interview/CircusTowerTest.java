package com.youngzy.leetcode.interview;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CircusTowerTest {

    CircusTower4 ct;

    @Before
    public void setUp() throws Exception {
        ct = new CircusTower4();
    }

    @Test
    public void test4() {
        int[] height = {3468,  3468,  3468,  3467,  3466,  3466,  3466,  3466};
        int[] weight = {4905,2659,8319,200,5020,2265,9954,2967};

        assertEquals(2, ct.bestSeqAtIndex(height, weight));
    }

    @Test
    public void test3() {
        int[] height = {0, 1, 2, 3, 4};
        int[] weight = {1, 6, 2, 3, 4};

        assertEquals(4, ct.bestSeqAtIndex(height, weight));
    }

    @Test
    public void test2() {
        int[] height = {2868,5485,1356,1306,6017,8941,7535,4941,6331,6181};
        int[] weight = {5042,3995,7985,1651,5991,7036,9391,428,7561,8594};

        assertEquals(5, ct.bestSeqAtIndex(height, weight));
    }

    @Test
    public void bestSeqAtIndex() {
        int[] height = {65,70,56,75,60,68};
        int[] weight = {100,150,90,190,95,110};

        assertEquals(6, ct.bestSeqAtIndex(height, weight));
    }
}