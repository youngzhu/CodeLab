package com.youngzy.stackskills.algorithms.sort;

public class QuickSort extends Sort {
    @Override
    void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }

        int pivotIdx = partition(arr, low, high);
        quickSort(arr, low, pivotIdx - 1);
        quickSort(arr, pivotIdx + 1, high);
    }

    /**
     * 返回支点的正确位置
     *
     * @param arr
     * @param low
     * @param high
     * @return
     */
    private int partition(int[] arr, int low, int high) {
        int pivot = arr[low];

        int l = low, h = high;

        while (l < h) {
            while (arr[l] <= pivot && l < h) {
                l++;
            }
            while (arr[h] > pivot) {
                h--;
            }
            if (l < h) {
                swap(arr, l, h);
            }

        }

        swap(arr, low, h);

        return h;
    }
}
