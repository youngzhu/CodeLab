package com.youngzy.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 442. 数组中重复的数据
 * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
 *
 * 找到所有出现两次的元素。
 *
 * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
 *
 * 示例：
 * 输入:
 * [4,3,2,7,8,2,3,1]
 *
 * 输出:
 * [2,3]
 *
 * 链接：https://leetcode-cn.com/problems/find-all-duplicates-in-an-array
 */
public class FindAllDuplicatesInArray {
    /*
    参考别人的
    有点绕，但能满足“不用额外空间并在O(n)时间完成”的要求

    val [1, n]
    val - 1，刚好满足数组的下标
    查看 num[val -1]，如果大于0，则取反（取反是为了便于恢复，取绝对值即可）
        如果小于0，说明val之前出现过
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList<>();

        for (int val : nums) {
            int abs = Math.abs(val);
            int idx = abs - 1;
            if (nums[idx] < 0) {
                ans.add(abs);
            } else {
                nums[idx] = - nums[idx];
            }

        }

        return ans;
    }
}
