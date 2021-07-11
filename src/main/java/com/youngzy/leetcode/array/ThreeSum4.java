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
 * 3 VS 4
 * 最大的问题在判断的先后上。
 * 首先应该判断sum是否为0
 * 而不是直接跳过相等的指针
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
public class ThreeSum4 implements IThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length < 3) {
            return ans;
        }

        Arrays.sort(nums);

        int i = 0;
        while (i < nums.length - 2 && nums[i] <= 0) {
            int l = i + 1, r = nums.length - 1;
            while (l < r) {

                int sum = nums[i] + nums[l] + nums[r];

                if (sum == 0) {
                    // == 0 最大，不能直接跳 l，r
                    ans.add(Arrays.asList(new Integer[]{nums[i], nums[l], nums[r]}));
                    r = moveLeft(nums, r, i);
                    l = moveRight(nums, l, r);
                    // 不能break
                    // 相同的i，可能有不同的 l，r
//                    break;
                } else if (sum > 0) {
                    r = moveLeft(nums, r, i);
                } else {
                    l = moveRight(nums, l, r);
                }
            }

            i = moveRight(nums, i, nums.length - 1);
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
