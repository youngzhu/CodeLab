package com.youngzy.stackskills.algorithms.sort;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SortTest {
    Sort sort;
    int[] before;
    int[] after = {-1, 0, 1, 3, 3, 8, 9};

    @Before
    public void setUp() throws Exception {
        // 保证每次test前都是无序的
        before = new int[]{9, 0, -1, 8, 3, 3, 1};
    }

    @Test
    public void selectionSort() {
        sort = new SelectionSort();
        sort.sort(before);
        assertArrayEquals(after, before);
    }

    @Test
    public void bubbleSort() {
        sort = new BubbleSort();
        sort.sort(before);
        assertArrayEquals(after, before);
    }

    @Test
    public void insertionSort() {
        sort = new InsertionSort();
        sort.sort(before);
        assertArrayEquals(after, before);
    }

    @Test
    public void shellSort() {
        sort = new ShellSort();
        sort.sort(before);
        assertArrayEquals(after, before);
    }

    @Test
    public void mergeSort() {
        sort = new MergeSort();
        sort.sort(before);
        assertArrayEquals(after, before);
    }

    @Test
    public void quickSort() {
        sort = new QuickSort();
        sort.sort(before);
        assertArrayEquals(after, before);
    }
}