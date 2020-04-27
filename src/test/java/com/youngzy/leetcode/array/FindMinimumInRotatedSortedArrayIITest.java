package com.youngzy.leetcode.array;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FindMinimumInRotatedSortedArrayIITest {

    FindMinimumInRotatedSortedArrayII ii;

    @Before
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