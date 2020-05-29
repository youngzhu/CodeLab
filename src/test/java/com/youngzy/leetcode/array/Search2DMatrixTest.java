package com.youngzy.leetcode.array;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Search2DMatrixTest {

    Search2DMatrix sm;

    @Before
    public void setUp() throws Exception {
        sm = new Search2DMatrix();
    }

    @Test
    public void test3() {
        int[][] matrix = {{1}, {3}};
        int target = 3;

        assertTrue(sm.searchMatrix(matrix, target));
    }

    @Test
    public void test2() {
        int[][] matrix = {{1}};
        int target = 1;

        assertTrue(sm.searchMatrix(matrix, target));
    }

    @Test
    public void test1() {
        int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,50}};
        int target = 3;

        assertTrue(sm.searchMatrix(matrix, target));
    }
    
    @Test
    public void test4() {
        int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,50}};
        int target = 11;

        assertTrue(sm.searchMatrix(matrix, target));
    }

    @Test
    public void test5() {
        int[][] matrix = {};
        int target = 0;

        assertFalse(sm.searchMatrix(matrix, target));
    }

    @Test
    public void test6() {
        int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,50}};
        int target = 30;

        assertTrue(sm.searchMatrix(matrix, target));
    }
}