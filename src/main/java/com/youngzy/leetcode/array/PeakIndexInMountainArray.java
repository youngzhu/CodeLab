package com.youngzy.leetcode.array;

/**
 * 我们把符合下列属性的数组 A 称作山脉：
 *
 * A.length >= 3
 * 存在 0 < i < A.length - 1 使得A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
 * 给定一个确定为山脉的数组，返回任何满足 A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1] 的 i 的值。
 *
 * 示例 1：
 * 输入：[0,1,0]
 * 输出：1
 *
 * 示例 2：
 * 输入：[0,2,1,0]
 * 输出：1
 *  
 * 提示：
 *
 * 3 <= A.length <= 10000
 * 0 <= A[i] <= 10^6
 * A 是如上定义的山脉
 *
 * 链接：https://leetcode-cn.com/problems/peak-index-in-a-mountain-array
 */
public class PeakIndexInMountainArray {

    /**
     * 折半查找
     *
     * 相邻不会存在相等的情况，即[0,0,0,0,1,0]
     *
     * @param A
     * @return
     */
    public int peakIndexInMountainArray(int[] A) {
        // 不可能出现在首尾，所以
        int low = 1, high = A.length - 2;

        int mid = low;
        while (low < high) {
            mid = low + (high - low) / 2;
            if (A[mid] < A[mid + 1]) {
                low = mid + 1;
                mid = mid + 1;
            } else {
                high = mid;
            }
        }

        return mid;

    }

    /**
     * 遍历，直到找到 A[i]>A[i+1]
     *
     * @param A
     * @return
     */
    public int peakIndexInMountainArray1(int[] A) {

        int i = 1;

        for (; i < A.length - 2; i++) {
            if (A[i] > A[i + 1]) {
                break;
            }
        }

        return i;
    }
}
