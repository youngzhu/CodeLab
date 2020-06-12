package com.youngzy.stackskills.algorithms.sort;

public class SelectionSort extends Sort {
    public void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    swap(array, i, j);
                }
            }
        }

    }

}
