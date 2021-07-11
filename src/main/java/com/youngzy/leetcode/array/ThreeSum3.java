package com.youngzy.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 学习别人的算法——双指针
 * 首先，排序
 *
 * i - l - r 的方式
 *
 * 还是卡在了重复项上，如 [0, 0, 0, 0]
 *
 * ========================
 * 全用正数，去掉取反的操作
 * 没啥意义。取反少不了。因为结果集里有正有负
 *
 * 用加法呢？
 * 没有用到加法。。
 *
 *
 * ================
 * 哈，初版测试通过
 *
 * 60 ms, 在所有 Java 提交中击败了12.55%的用户
 * 41.8 MB, 在所有 Java 提交中击败了99.40%的用户
 */
public class ThreeSum3 implements IThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length < 3) {
            return ans;
        }

        Arrays.sort(nums);

        int i = 0;
        while (i < nums.length - 2 && nums[i] < 0) {
            int l = i + 1, r = nums.length - 1;
            while (l < r) {

                int sum = nums[i] + nums[l] + nums[r];

                if (sum > 0) {
                    r = moveLeft(nums, r, i);
                } else if (sum < 0) {
                    l = moveRight(nums, l, r);
                } else {
                    ans.add(Arrays.asList(new Integer[]{nums[i], nums[l], nums[r]}));
                    break;
                }
            }

            i = moveRight(nums, i, r);
        }


        return ans;
    }

    /**
     * 移动下标，直至最近的不相等的值
     *
     * @param nums
     * @param idx
     * @return
     */
    private int moveLeft(int[] nums, int idx, int min) {
        while (idx > min && nums[idx] == nums[--idx]) {}
        return idx;
    }
    private int moveRight(int[] nums, int idx, int max) {
        while (idx < max && nums[idx] == nums[++idx]) {}
        return idx;
    }

}
