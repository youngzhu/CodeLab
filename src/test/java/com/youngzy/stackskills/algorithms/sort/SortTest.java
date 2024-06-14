package com.youngzy.stackskills.algorithms.sort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SortTest {
    Sort sort;
    int[] before;
    int[] after = {-1, 0, 1, 3, 3, 8, 9};

    @BeforeEach
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

    @Test
    public void heapSort() {
        sort = new HeapSort();
        sort.sort(before);
        assertArrayEquals(after, before);
    }
}