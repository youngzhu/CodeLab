package com.youngzy.stackskills.algorithms.sort;

public class BinarySearch {
    public int search(int[] sorted, int target) {
        int low = 0;
        int high = sorted.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (sorted[mid] == target) {
                return mid;
            }

            if (sorted[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }
}
