package com.youngzy.leetcode.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 示例:
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 *
 * 链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram
 */
public class LargestRectangle {

    /**
     * 学习别人的单调栈
     * 关键点：
     *  maxArea[i] = heights[i] * (right - left - 1)
     *  right: 右边第一个小于 heights[i] 的值
     *  left: 左边第一个小于 heights[i] 的值
     *
     * 测试结果：空间提升不多，但时间相差巨大
     *  执行用时：10 ms, 在所有 Java 提交中击败了91.23% 的用户
     *  内存消耗：39.7 MB, 在所有 Java 提交中击败了86.07% 的用户
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        int answer = 0;

        // 为了左右两边都有小值，原数组头尾各增加一个0
        int[] newHeights = new int[heights.length + 2];
        for (int i = 1; i < newHeights.length - 1; i ++) {
            newHeights[i] = heights[i - 1];
        }

        Deque<Integer> stack = new LinkedList<Integer>();
        // 必须全部遍历，这里的i是左右值，而不是当前值
        for (int i = 0; i < newHeights.length; i ++) {
            while (! stack.isEmpty() && newHeights[stack.peek()] > newHeights[i]) {
                int cur = stack.pop();
                int left = stack.peek();
                int area = newHeights[cur] * (i - left - 1);
                answer = Math.max(answer, area);
            }

            stack.push(i);
        }

        return answer;
    }
    /**
     * 在上一版本的基础上优化一下循环代码
     *
     * 测试结果：时间更长了。。。空间变化不大
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea1(int[] heights) {
        int answer = 0;
        for (int start = 0; start < heights.length; start++) {
            int minHeight = heights[start]; // 最小的高度
            int end = start;
            while (end < heights.length) {
                // 更新高度最小值
                if (minHeight > heights[end]) {
                    minHeight = heights[end];
                }
                int len = end - start + 1;
                int area = len * minHeight;
                if (answer < area) {
                    // 更新最大面积值
                    answer = area;
                }

                end++;
            }
        }

        return answer;
    }

    /**
     * 把自己给厉害的，一次性通过
     * 虽然效率不怎么样：
     * 执行用时：745 ms, 在所有 Java 提交中击败了27.13% 的用户
     * 内存消耗：40.6 MB, 在所有 Java 提交中击败了13.17% 的用户
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea0(int[] heights) {
        int answer = 0;
        for (int start = 0; start < heights.length; start++) {
            // 先算自身的面积
            int area = heights[start];
            if (answer < area) {
                // 更新最大面积值
                answer = area;
            }
            int minHeight = heights[start]; // 最小的高度
            for (int end = start + 1; end < heights.length; end++) {
                // 更新高度最小值
                if (minHeight > heights[end]) {
                    minHeight = heights[end];
                }
                int len = end - start + 1;
                area = len * minHeight;
                if (answer < area) {
                    // 更新最大面积值
                    answer = area;
                }
            }
        }

        return answer;
    }
}
