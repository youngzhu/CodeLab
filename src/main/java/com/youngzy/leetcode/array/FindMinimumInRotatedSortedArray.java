package com.youngzy.leetcode.array;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 请找出其中最小的元素。
 *
 * 你可以假设数组中不存在重复元素。
 *
 * 示例 1:
 * 输入: [3,4,5,1,2]
 * 输出: 1
 *
 * 示例 2:
 * 输入: [4,5,6,7,0,1,2]
 * 输出: 0
 *
 *
 * 链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array
 */
public class FindMinimumInRotatedSortedArray {

    /**
     * 跟之前的找峰值很像
     * 极大值后面即是极小值
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {

        int len = nums.length;

        if (len == 1) {
            return nums[0];
        } else {
            int peakIdx = 0;
            int low = 0, high = len - 1;

            while (low < high) {
                peakIdx = low + (high - low) / 2;

                if (nums[peakIdx] < nums[peakIdx + 1]) {
                    low = peakIdx + 1;
                } else {
                    high = peakIdx;
                }

            }

            return nums[peakIdx + 1];
        }

    }
}
