package com.youngzy.stackskills.algorithms.sort;

public class BubbleSort extends Sort {
    @Override
    void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            // 如果未发生过交换，则表示本身就是有序的
            // 结束
            boolean swapped = false;

            for (int j = array.length - 1; j > i; j--) {
                // 相邻的两两比较
                // 如果不是有序的，则交换
                if (array[j] < array[j - 1]) {
                    swap(array, j, j-1);
                    swapped = true;
                }
            }

            if (! swapped) {
                break;
            }
        }
    }
}
