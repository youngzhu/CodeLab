package com.youngzy.leetcode.array;

/**
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 *
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 *
 * 你可以返回任何满足上述条件的数组作为答案。
 *
 * 示例：
 * 输入：[4,2,5,7]
 * 输出：[4,5,2,7]
 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 *
 * 提示：
 * 2 <= A.length <= 20000
 * A.length % 2 == 0
 * 0 <= A[i] <= 1000
 *
 * 链接：https://leetcode-cn.com/problems/sort-array-by-parity-ii
 */
public class SortArrayByParityII {

    /**
     * 从头遍历数组，跟数组尾部的数据做交换
     *
     * @param A
     * @return
     */
    public int[] sortArrayByParityII(int[] A) {
        int cur = 0, tailEven = A.length - 2, tailOdd = A.length - 1;
        int tmp;

        for (;cur <= tailEven || cur <= tailOdd; cur ++) {
            if (A[cur] % 2 == 1 && cur % 2 == 0) {
                // 偶数位，奇数值
                while (tailOdd > cur) {
                    if (A[tailOdd] % 2 == 0) {
                        tmp = A[tailOdd];
                        A[tailOdd] = A[cur];
                        A[cur] = tmp;

                        break;
                    }

                    tailOdd -= 2;
                }

            } else if (A[cur] % 2 == 0 && cur % 2 == 1) {
                // 奇数位，偶数值
                while (tailEven > cur) {
                    if (A[tailEven] % 2 == 1) {
                        tmp = A[tailEven];
                        A[tailEven] = A[cur];
                        A[cur] = tmp;

                        break;
                    }

                    tailEven -= 2;
                }
            }
        }

        return A;
    }

    public int[] sortArrayByParityII1(int[] A) {
        int[] arr = new int[A.length];

        int evenIdx = 0,  oddIdx = 1, idx;

        for (int i : A) {
            if (i % 2 == 0) {
                idx = evenIdx;
                evenIdx += 2;
            } else {
                idx = oddIdx;
                oddIdx += 2;
            }

            arr[idx] = i;
        }

        return arr;
    }

}
