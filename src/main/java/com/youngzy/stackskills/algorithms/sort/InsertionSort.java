package com.youngzy.stackskills.algorithms.sort;

public class InsertionSort extends Sort {
    @Override
    void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            // sublist(0) 有序
            // 从第二个元素开始比较
            for (int j = i + 1; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    swap(array, j, j -1);
                } else {
                    break;
                }

            }
        }
    }
}
