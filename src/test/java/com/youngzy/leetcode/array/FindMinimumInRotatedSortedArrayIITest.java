package com.youngzy.leetcode.array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FindMinimumInRotatedSortedArrayIITest {

    FindMinimumInRotatedSortedArrayII ii;

    @BeforeEach
    public void setUp() throws Exception {
        ii = new FindMinimumInRotatedSortedArrayII();
    }

    @Test
    public void test1() {
        assertEquals(0, ii.findMin(new int[]{2,2,2,0,1}));
    }

    @Test
    public void test2() {
        assertEquals(1, ii.findMin(new int[]{3,3,1,3}));
    }

    @Test
    public void test3() {
        assertEquals(1, ii.findMin(new int[]{1,3, 3}));
    }
}