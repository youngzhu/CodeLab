package com.youngzy.stackskills.algorithms.sort;

abstract class Sort {
    abstract void sort(int[] array);

    void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
