package com.youngzy.stackskills.algorithms.sort;

public class MergeSort extends Sort {
    @Override
    void sort(int[] array) {
//        System.out.println("Merge Sort");

        if (array.length == 1) {
            return;
        }

        int mid = array.length / 2;
        int[] halfArr = new int[mid];
        int[] anotherHalf = new int[array.length - mid];

        split(array, halfArr, anotherHalf);

        sort(halfArr);
        sort(anotherHalf);

        merge(array, halfArr, anotherHalf);
    }

    private void split(int[] arr, int[] half, int[] anotherHalf) {
        int idx = 0;
        int anotherStartIndex = half.length;

        for (int i : arr) {
            if (idx < anotherStartIndex) {
                half[idx] = i;
            } else {
                anotherHalf[idx - anotherStartIndex] = i;
            }

            idx ++;
        }

    }

    private void merge(int[] arr, int[] half, int[] anotherHalf) {
        int mergeIdx = 0;
        int halfIdx = 0;
        int anotherHalfIdx = 0;

        int val;
        while (halfIdx < half.length
                && anotherHalfIdx < anotherHalf.length) {
            if (half[halfIdx] < anotherHalf[anotherHalfIdx]) {
                val = half[halfIdx];
                halfIdx++;
            } else {
                val = anotherHalf[anotherHalfIdx];
                anotherHalfIdx ++;
            }

            arr[mergeIdx++] = val;
        }

        while (halfIdx < half.length) {
            arr[mergeIdx++] = half[halfIdx++];
        }

        while (anotherHalfIdx < anotherHalf.length) {
            arr[mergeIdx++] = anotherHalf[anotherHalfIdx++];
        }
    }
}
