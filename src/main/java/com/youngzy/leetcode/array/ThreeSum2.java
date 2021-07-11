package com.youngzy.leetcode.array;

import java.util.*;

/**
 * 学习别人的算法——双指针
 * 首先，排序
 *
 * L - C - R 的方式，遇到相等的值的下标该不该跳过不好判断
 * 难怪优选算法中没有用这种方式
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
public class ThreeSum2 implements IThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length < 3) {
            return ans;
        }

        Arrays.sort(nums);

        int c = 1;
        while (c < nums.length - 1) {
            int l = 0, r = nums.length - 1;

            while (l < c && c < r && nums[l] <= 0) {
                int sum = nums[l] + nums[c] + nums[r];
                if (sum > 0) {
                    r = moveLeft(nums, r);
                } else if (sum < 0) {
                    l  = moveRight(nums, l);
                } else {
                    ans.add(Arrays.asList(new Integer[]{nums[l], nums[c], nums[r]}));
                    l = moveRight(nums, l);
                    r = moveLeft(nums, r);
                }
            }

//            c = moveRight(nums, c);
            c++; // C 不能跳过，它是主体
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
    private int moveLeft(int[] nums, int idx) {
        while (nums[idx] == nums[--idx]  && idx > 0) {}
        return idx;
    }
    private int moveRight(int[] nums, int idx) {
        while (nums[idx] == nums[++idx] && idx < nums.length - 1) {}
        return idx;
    }
}
