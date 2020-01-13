package com.youngzy.leetcode.array;

/**
 * 给定一个非负整数数组 A，返回一个数组，在该数组中， A 的所有偶数元素之后跟着所有奇数元素。
 *
 * 你可以返回满足此条件的任何数组作为答案。
 *
 * 示例：
 * 输入：[3,1,2,4]
 * 输出：[2,4,3,1]
 * 输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。
 *
 * 提示：
 * 1 <= A.length <= 5000
 * 0 <= A[i] <= 5000
 *
 * 链接：https://leetcode-cn.com/problems/sort-array-by-parity
 */
public class SortArrayByParity {

    public int[] sortArrayByParity(int[] A) {
        int head = 0, tail = A.length - 1;
        int tmp;

        for (; head < tail; head++) {
            if (A[head] % 2 == 1) {
                // 奇数则交换
                for (; tail > head; tail --) {
                    if (A[tail] % 2 == 0) {
                        // 如果是偶数，则跟头部的奇数做交换
                        tmp = A[tail];
                        A[tail] = A[head];
                        A[head] = tmp;

                        // 完成交换后，下次尾部从当前位置的下一个开始
                        tail--;
                        break;
                    }
                }
            }
        }

        return A;
    }

    /**
     * 遍历数组
     * 偶数放在新数组的前面，奇数放后面
     *
     * @param A
     * @return
     */
    public int[] sortArrayByParity1(int[] A) {
        int[] arr = new int[A.length];
        int start = 0, end = A.length - 1;

        for (int i : A) {
            if (i % 2 == 0) {
                arr[start++] = i;
            } else {
                arr[end--] = i;
            }
        }

        return arr;
    }
}
