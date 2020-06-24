package com.youngzy.leetcode.interview;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class SmallestKTest {

    private SmallestK sk;

    @Before
    public void setUp() throws Exception {
        sk = new SmallestK();
    }

    @Test
    public void smallestK() {
        int[] arr = {1,3,5,7,2,4,6,8};

        int[] result = sk.smallestK(arr, 4);
        Set<Integer> actual = new HashSet<>(result.length);
        for (int i : result) {
            actual.add(i);
        }

        Set<Integer> excepted = new HashSet<>(Arrays.asList(1,2,3,4));


        assertEquals(excepted, actual);
    }
}