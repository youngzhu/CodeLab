package com.youngzy.leetcode.sort;

/**
 * 75. 颜色分类
 *
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，
 * 原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 *
 * 示例:
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 *
 * 进阶：
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 *
 * 链接：https://leetcode-cn.com/problems/sort-colors
 */
public class SortColors {
    /**
     * 官解
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        int p0 = 0; // 已遍历过的数据中，0 最大的index（右移 + 1）
        int p2 = nums.length - 1; // 同上，左移 - 1
        int p = 0; // 移动指针

        while (p <= p2) {
            if (nums[p] == 0) {
                // 加上这样的判断，几乎没影响（提高）
                if (nums[p0] == 0) {
                    if (p == p0) {
                        p ++;
                    }
                    p0 ++;
                    continue;
                }
                swap(nums, p++, p0++);
            } else if (nums[p] == 2) {
                swap(nums, p, p2 --);
            } else {
                p ++;
            }
        }
    }

    public void sortColors0(int[] nums) {

        for (int i = 0; i < nums.length; i ++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    swap(nums, i, j);
                }
            }
        }
    }

    void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
