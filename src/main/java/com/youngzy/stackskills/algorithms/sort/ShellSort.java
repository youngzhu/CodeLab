package com.youngzy.stackskills.algorithms.sort;

public class ShellSort extends Sort {


    private void insertionSort(int[] array, int start, int increment) {
        for (int i = start; i < array.length; i = i + increment) {
            // sublist(0) 有序
            // 从第二个元素开始比较
            for (int j = Math.min(array.length - 1, i + increment);
                 j - increment >= 0;
                 j = j - increment) {
                if (array[j] < array[j - increment]) {
                    swap(array, j, j -increment);
                } else {
                    break;
                }

            }
        }

    }

    @Override
    void sort(int[] array) {
        int increment = array.length / 2; // 随便选的，没有完美的选择

        while (increment > 0) {
            for (int start = 0; start < increment; start++) {
                insertionSort(array, start, increment);
            }

            increment = increment / 2;
        }

    }
}
