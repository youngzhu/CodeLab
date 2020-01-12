package com.youngzy.leetcode.array;

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
