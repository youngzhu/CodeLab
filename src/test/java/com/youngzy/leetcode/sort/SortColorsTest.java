package com.youngzy.leetcode.sort;

import org.junit.Test;

import static org.junit.Assert.*;

public class SortColorsTest {

    SortColors sc = new SortColors();

    @Test
    public void test() {
        int[] input = {2, 0, 1};

        int[] expected = {0, 1, 2};
        sc.sortColors(input);

        assertArrayEquals(expected, input);
    }

    @Test
    public void sortColors() {
        int[] input = {2, 0, 2, 1, 1, 0};

        int[] expected = {0, 0, 1, 1, 2, 2};
        sc.sortColors(input);

        assertArrayEquals(expected, input);
    }
}