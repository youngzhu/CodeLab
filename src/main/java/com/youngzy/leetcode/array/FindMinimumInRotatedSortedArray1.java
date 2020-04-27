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
public class FindMinimumInRotatedSortedArray1 {

    /**
     * 通过首尾比较，判断是否做了旋转
     *
     * 结果：没啥改善
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {

        // 二分法，最少3个元素
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.min(nums[0], nums[1]);
        }

        // 未旋转
        if (nums[0] < nums[nums.length - 1]) {
            return nums[0];
        }

        int peakIdx = getPeakIdx(nums);

        return nums[peakIdx + 1];

    }

    private int getPeakIdx(int[] nums) {
        int peakIdx = 0;
        int low = 0, high = nums.length - 1;

        while (low < high) {
            peakIdx = low + (high - low) / 2;

            // 峰值
            // 条件1：比左边的大
            boolean case1 = nums[peakIdx] > nums[peakIdx + 1];
            // 条件2：比右边的大 或者 是第一个元素
            boolean case2 = peakIdx == 0 || nums[peakIdx] > nums[peakIdx -1];
            if (case1 && case2) {
                return peakIdx;
            }

            // 防止两个向上直线，左边很短右边很长的情况
            // 如 5 6 1 2 3 4
            // 取中值后，还是上升趋势，但不能移动 low
            boolean alwaysUp = nums[0] < nums[peakIdx];
            // 处于上升趋势，则移动 low
            if (alwaysUp
                    && nums[peakIdx] < nums[peakIdx + 1]
                    && nums[peakIdx] > nums[peakIdx - 1]) {
                low = peakIdx + 1;
            } else {
                high = peakIdx;
            }

        }
        return -1;
    }
}
