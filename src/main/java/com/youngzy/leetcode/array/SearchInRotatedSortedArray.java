package com.youngzy.leetcode.array;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * 你可以假设数组中不存在重复的元素。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 *
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 */
public class SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int low = 0, high = nums.length - 1;
        int mid;
        while (low <= high) {
            mid = low + (high - low)/2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[low] <= nums[mid]) {
                // 前半部有序
                // 后面没=，是因为前面已经return
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                // 后半部有序
                if (target <= nums[high] && target > nums[mid]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }

            }
        }

        return -1;
    }

    /**
     * 先找到中间的峰值（二分法）
     *
     * 失败！！
     *
     * @param nums
     * @param target
     * @return
     */
    public int search0(int[] nums, int target) {

        int len = nums.length;

        int ans = -1;

        if (len < 3) {
            for (int i = 0; i < len; i++) {
                if (nums[i] == target) {
                    ans = i;
                    break;
                }
            }
        } else {
            int low = 0, high = nums.length - 1;

            int mid = low;
            while (low < high) {
                mid = low + (high - low) / 2;

                if (nums[mid] < nums[mid + 1]) {
                    mid = mid + 1;
                    low = mid;
                } else {
                    high = mid;
                }
            }

            int peakIdx = mid; // 峰值索引

            if (nums[0] <= target && nums[peakIdx] >= target) {
                for (int i = 0; i <= peakIdx; i++) {
                    if (target == nums[i]) {
                        ans = i;
                        break;
                    }
                }

            } else if (target >= nums[peakIdx + 1] && target <= nums[nums.length - 1]) {
                for (int i = peakIdx + 1; i < nums.length ; i++) {
                    if (target == nums[i]) {
                        ans = i;
                        break;
                    }
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;

        SearchInRotatedSortedArray obj = new SearchInRotatedSortedArray();
        obj.search(nums, target);
    }
}
