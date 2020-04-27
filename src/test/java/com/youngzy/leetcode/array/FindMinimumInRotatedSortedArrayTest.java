package com.youngzy.leetcode.array;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FindMinimumInRotatedSortedArrayTest {

    FindMinimumInRotatedSortedArray2 fm;

    @Before
    public void setUp() throws Exception {
        fm = new FindMinimumInRotatedSortedArray2();
    }

    @Test
    public void test7() {
        assertEquals(1, fm.findMin(new int[]{7,8,1,2,3,4,5,6}));
    }

    @Test
    public void test6() {
        assertEquals(1, fm.findMin(new int[]{1,2}));
    }

    @Test
    public void test5() {
        assertEquals(1, fm.findMin(new int[]{5,1,2,3,4}));
    }

    @Test
    public void test4() {
        assertEquals(3, fm.findMin(new int[]{3}));
    }

    @Test
    public void test3() {
        assertEquals(1, fm.findMin(new int[]{3, 1,2}));
    }

    @Test
    public void test2() {
        assertEquals(1, fm.findMin(new int[]{1,2,3}));
    }

    @Test
    public void test1() {
        assertEquals(1, fm.findMin(new int[]{3,4,5,1,2}));
    }
}