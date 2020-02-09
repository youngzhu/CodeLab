package com.youngzy.leetcode.array;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
 *
 * 跟之前的区别在于存在重复数据！！！
 *
 * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
 *
 * 示例 1:
 *
 * 输入: nums = [2,5,6,0,0,1,2], target = 0
 * 输出: true
 * 示例 2:
 *
 * 输入: nums = [2,5,6,0,0,1,2], target = 3
 * 输出: false
 *
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii
 */
public class SearchInRotatedSortedArrayII {

    /**
     * 三类情况：
     *
     * 第一类
     * 10111 和 11101 这种。
     * 此种情况下 nums[start] == nums[mid]，分不清到底是前面有序还是后面有序，此时 start++ 即可。相当于去掉一个重复的干扰项。
     * 第二类
     * 2345671  这种，也就是 nums[start] < nums[mid]。此例子中就是 2 < 5；
     * 这种情况下，前半部分有序。因此如果 nums[start] <=target<nums[mid]，则在前半部分找，否则去后半部分找。
     * 第三类
     * 6712345 这种，也就是 nums[start] > nums[mid]。此例子中就是 6 > 2；
     * 这种情况下，后半部分有序。因此如果 nums[mid] <target<=nums[end]。则在后半部分找，否则去前半部分找。
     *
     * @param nums
     * @param target
     * @return
     */
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int low = 0, high = nums.length - 1;
        int mid;
        while (low <= high) {
            mid = low + (high - low)/2;

            if (nums[mid] == target) {
                return true;
            }

            if (nums[low] == nums[mid]) {
                low++;
            } else if (nums[low] < nums[mid]) {
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

        return false;
    }

}
