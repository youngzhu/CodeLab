package com.youngzy.leetcode.interview;

import java.util.Arrays;
import java.util.Stack;

/**
 * 面试题 17.08. 马戏团人塔
 *
 * 有个马戏团正在设计叠罗汉的表演节目，一个人要站在另一人的肩膀上。
 * 出于实际和美观的考虑，在上面的人要比下面的人矮一点且轻一点。
 * 已知马戏团每个人的身高和体重，请编写代码计算叠罗汉最多能叠几个人。
 *
 * 示例：
 * 输入：height = [65,70,56,75,60,68] weight = [100,150,90,190,95,110]
 * 输出：6
 * 解释：从上往下数，叠罗汉最多能叠 6 层：(56,90), (60,95), (65,100), (68,110), (70,150), (75,190)
 *
 * 提示：height.length == weight.length <= 10000
 *
 * 链接：https://leetcode-cn.com/problems/circus-tower-lcci
 */
public class CircusTower {

    /**
     * 二叉树
     * 原地排序
     *
     * 弃了，重开一个，准备用 堆 做一下
     *
     * @param height
     * @param weight
     * @return
     */
    public int bestSeqAtIndex(int[] height, int[] weight) {
        // 选择排序
        selectionSort(height, weight);
        // 冒泡排序
        // 插入排序
        // Shell 排序
        // 合并排序
        // 快速排序

        print(height);
        print(weight);

        Stack<Integer> stack = new Stack<>();

        bestSeq(weight, 0 , weight.length - 1, stack);

        return stack.size();
    }

    /**
     * 本以为很有希望的，结果还是失败了
     *
     * 基本思路是：用Stack存储满足条件的数，从小到大
     * 如果当前值 小于 stack 中的最小值，直接 入栈
     * 如果 大于 最小值，且 小于 第二小的值，则将最小值用 当前值替换
     *
     * 结果败在了类似这样的数据上：9644，849， 3232
     * 当前值：9644； Stack：849，3232
     * 按之前的算法，以二换一，不划算。
     * 但实际上就应该是 Stack：9644
     *
     * @param height
     * @param weight
     * @return
     */
    public int bestSeqAtIndex1(int[] height, int[] weight) {
        // 选择排序
        selectionSort(height, weight);
        // 冒泡排序
        // 插入排序
        // Shell 排序
        // 合并排序
        // 快速排序

        print(height);
        print(weight);

        Stack<Integer> stack = new Stack<>();

        bestSeq(weight, 0 , weight.length - 1, stack);

        return stack.size();
    }

    /**
     * 思路：
     * 1. 先将身高 或 体重严格排序（同步更改另一个元素的位置）
     * 2. 转换为最长子串问题：找到在身高严格排序的情况下，体重也是严格排序的最长子串
     *
     * 犯的错误：
     * 1. 以为是要连续的。其实不用
     *
     * @param height
     * @param weight
     * @return
     */
    public int bestSeqAtIndex0(int[] height, int[] weight) {
        // 当初的代码已经没有
        // 从现在开始，每个想法都保留一下（commit）
        // 留个纪念
        return 0;
    }

    private void bestSeq(int[] weight, int start, int end, Stack<Integer> stack) {
        int current = weight[start]; // 当前值

        if (start == end) {
            stack.push(current);
            return ;
        }

        // 后面子串的最大值
        bestSeq(weight, start + 1, end, stack);


        int min = stack.pop(); // 最小值
        int secMin = stack.isEmpty() ? min : stack.peek(); // 第二小的值

        if (current < min) {
            stack.push(min);
            stack.push(current);
        } else if (current > min
                && (current < secMin || min == secMin)) {
            stack.push(current);
        } else {
            stack.push(min);
        }
    }

    private void print(int[] height) {
        System.out.println(Arrays.toString(height));
    }

    /**
     * 选择排序：每次遍历后选出剩余元素中最小的一个
     *
     * @param height
     * @param weight
     */
    private void selectionSort(int[] height, int[] weight) {
        for (int i = 0; i < height.length; i ++) {
            for (int j = i + 1; j < height.length; j ++) {
                if (height[j] < height[i]) {
                    swap(height, i, j);
                    swap(weight, i, j);
                }
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
