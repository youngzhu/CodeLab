package com.youngzy.leetcode.array;

import java.util.Stack;

/**
 * 238. Product of Array Except Self
 *
 * Given an array nums of n integers where n > 1,  
 * return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 *
 * Example:
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 *
 * Constraint: 
 * It's guaranteed that the product of the elements of
 * any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.
 *
 * Note: Please solve it without division and in O(n).
 *
 * Follow up:
 * Could you solve it with constant space complexity?
 * (The output array does not count as extra space for the purpose of space complexity analysis.)
 *
 * 链接：https://leetcode-cn.com/problems/product-of-array-except-self
 */
public class ProductExceptSelf {

    /**
     * 左右乘积法（升级版，空间复杂度 O(1)）
     *
     * 直接用返回数组 result 充当 左乘积数组
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];

        result[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        // 右侧乘积
        // 从最右端算起
        int rightProduct = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            rightProduct *= nums[i + 1];

            result[i] = result[i] * rightProduct;
        }

        return result;
    }

    /**
     * 左右乘积法
     *
     * 新增2个数组：left，right
     * left[i]，表示 nums[i] 左边所有数字的乘积
     * right[i]，表示 nums[i] 左边所有数字的乘积
     *
     * result[i] = left[i] * right[i]
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf2(int[] nums) {
        int[] result = new int[nums.length];

        int[] left = new int[nums.length];
        int[] right = new int[nums.length];

        left[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }

        right[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < nums.length; i++) {
            result[i] = left[i] * right[i];
        }

        return result;
    }

    /**
     * 超时了。。。
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf1(int[] nums) {
        int[] result = new int[nums.length];

        int head = 0, tail = 0;

        while (head < nums.length) {
            int product = 1;
            tail = (head + 1) % nums.length;

            while (tail != head) {
                product *= nums[tail];

                tail = (tail + 1) % nums.length;
            }

            result[head++] = product;
        }

        return result;
    }


    /**
     * O(N^2)
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf0(int[] nums) {
        int[] result = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int product = 1;
            for (int j = 0; j < nums.length; j++) {
                if (i != j) {
                    product *= nums[j];
                }
            }
            result[i] = product;
        }

        return result;
    }
}
