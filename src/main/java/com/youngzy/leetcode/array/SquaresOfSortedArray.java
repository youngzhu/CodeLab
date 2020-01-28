package com.youngzy.leetcode.array;

/**
 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 *
 * 示例 1：
 * 输入：[-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 *
 * 示例 2：
 * 输入：[-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 *
 * 提示：
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A 已按非递减顺序排序。
 *
 * 链接：https://leetcode-cn.com/problems/squares-of-a-sorted-array
 */
public class SquaresOfSortedArray {

    /**
     * 2的基础上再优化
     * 优化点：全正，全负的情形
     *
     * @param A
     * @return
     */
    public static int[] sortedSquares(int[] A) {
        int[] ans = new int[A.length];

        int negativeIdx = 0, positiveIdx = 0, idx = 0;

        // 找出负数的最大下标
        while (idx < A.length && A[idx] < 0) {
            idx ++;
        }
        negativeIdx = idx - 1;
        positiveIdx = idx;

        idx = 0;

        // 全负
        if (negativeIdx == A.length - 1) {
            while (negativeIdx >= 0) {
                ans[idx++] = A[negativeIdx] * A[negativeIdx];
                negativeIdx --;
            }

            return ans;
        }

        // 全正
        if (negativeIdx == - 1) {
            while (idx < A.length) {
                ans[idx] = A[idx] * A[idx];
                idx ++;
            }

            return ans;
        }

        // 有正有负
        while (negativeIdx >= 0 && positiveIdx < A.length) {
            if (A[negativeIdx] * A[negativeIdx] < A[positiveIdx] * A[positiveIdx]) {
                ans[idx++] = A[negativeIdx] * A[negativeIdx];
                negativeIdx --;
            } else {
                ans[idx ++] = A[positiveIdx] * A[positiveIdx];
                positiveIdx ++;
            }
        }

        while (negativeIdx >= 0) {
            ans[idx++] = A[negativeIdx] * A[negativeIdx];
            negativeIdx --;
        }

        while (positiveIdx < A.length) {
            ans[idx ++] = A[positiveIdx] * A[positiveIdx];
            positiveIdx ++;
        }

        return ans;
    }

    /**
     * 参考官解：双指针法
     *
     * 跟自己的方法比起来，大概就优在没有嵌套循环上
     *
     * @param A
     * @return
     */
    public static int[] sortedSquares2(int[] A) {
        int[] ans = new int[A.length];

        int negativeIdx = 0, positiveIdx = 0, idx = 0;

        // 找出负数的最大下标
        while (idx < A.length && A[idx] < 0) {
            idx ++;
        }
        negativeIdx = idx - 1;
        positiveIdx = idx;

        idx = 0;

        while (negativeIdx >= 0 && positiveIdx < A.length) {
            if (A[negativeIdx] * A[negativeIdx] < A[positiveIdx] * A[positiveIdx]) {
                ans[idx++] = A[negativeIdx] * A[negativeIdx];
                negativeIdx --;
            } else {
                ans[idx ++] = A[positiveIdx] * A[positiveIdx];
                positiveIdx ++;
            }
        }

        while (negativeIdx >= 0) {
            ans[idx++] = A[negativeIdx] * A[negativeIdx];
            negativeIdx --;
        }

        while (positiveIdx < A.length) {
            ans[idx ++] = A[positiveIdx] * A[positiveIdx];
            positiveIdx ++;
        }

        return ans;
    }

    public static int[] sortedSquares1(int[] A) {
        int[] negativeArr = new int[A.length];
        int[] positiveArr = new int[A.length];
        int[] arr = new int[A.length];

        int negativeIdx = 0, positiveIdx = 0, idx = 0;

        // 找出负数
        for (int i : A) {
            if (i >= 0) {
                break;
            }
            negativeArr[negativeIdx ++] = i * i;
        }

        if (negativeIdx == A.length) {
            // 都是负数
            while (--negativeIdx >= 0) {
                arr[idx ++] = negativeArr[negativeIdx];
            }

            return arr;
        }

        positiveIdx = negativeIdx;

        for (;positiveIdx < A.length; positiveIdx ++) {
            arr[idx++] = A[positiveIdx] * A[positiveIdx];
        }

        if (negativeIdx == 0) {
            // 全是正数
            return arr;
        }

        // 正负都有
        for (int i = 0; i < negativeIdx; i++) {
            int tmpIdx = idx - 1;

            if (arr[tmpIdx] > negativeArr[i]) {
                // 需要插入
                while (tmpIdx >= 0 && arr[tmpIdx] > negativeArr[i]) {
                    arr[tmpIdx + 1] = arr[tmpIdx];
                    tmpIdx--;
                }

            }
            arr[tmpIdx + 1] = negativeArr[i];

            idx ++;
        }

        return arr;
    }

    public static void main(String[] args) {
//        int[] arr = {-4, -1, 0, 3, 10};
        int[] arr = {-1};

        sortedSquares(arr);

    }
}
