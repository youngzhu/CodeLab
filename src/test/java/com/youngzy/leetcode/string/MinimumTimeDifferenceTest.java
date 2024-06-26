package com.youngzy.leetcode.string;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MinimumTimeDifferenceTest {

    MinimumTimeDifference mtd;

    @BeforeEach
    public void setUp() throws Exception {
        mtd = new MinimumTimeDifference();
    }

    /*
    超出了String限制
     */
    @Test
    public void test3() {
        int actual = mtd.findMinDifference(Arrays.asList("01:01","02:01"));
        assertEquals(60, actual);
    }

    @Test
    public void test2() {
        int actual = mtd.findMinDifference(Arrays.asList("00:00", "23:59", "00:00"));
        assertEquals(0, actual);
    }

    @Test
    public void test1() {
        int actual = mtd.findMinDifference(Arrays.asList("00:00", "23:59"));
        assertEquals(1, actual);
    }
}