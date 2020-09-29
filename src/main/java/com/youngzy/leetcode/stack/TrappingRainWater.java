package com.youngzy.leetcode.stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;

/**
 * 42. 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 示例:
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 *
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 */
public class TrappingRainWater {
    /**
     * 栈
     * 大概3ms，测试下来，动态规划是最高效的
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int answer = 0;

        // 栈顶 相当于动态规划的 右侧最高
        // 底部相当于左侧最高
        Deque<Integer> stack = new LinkedList<Integer>();
        int currentIdx = 0;
        while (currentIdx < height.length) {
            while (!stack.isEmpty()
                    && height[currentIdx] > height[stack.peek()]) {
                // 当前要计算的下标的高度
                int h = height[stack.pop()];

                // 左右都有高墙才能存水，只有一边的跳过
                if (stack.isEmpty()) {
                    break;
                }
                int l = currentIdx - stack.peek() - 1;
                // 计算左右两边较小的高度
                int min = Math.min(height[stack.peek()], height[currentIdx]);
                answer += l * (min - h);

            }
            stack.push(currentIdx);
            currentIdx ++;
        }

        return answer;
    }

    /**
     * 动态规划进阶版：双指针
     * 测试结果：
     *  VS 动态规划，空间差不多，时间：2ms:1ms
     *
     * @param height
     * @return
     */
    public int trap3(int[] height) {
        int answer = 0;

        int maxHeightL = 0; // 左指针
        // 右指针不能像左指针一样 做简单的替换
        // 因为左指针是从左到右遍历
        // 而右指针需要从右至左
        int maxHeightR = 0; // 右指针
        int leftIdx = 1;
        int rightIdx = height.length - 2;

        for (int i = 1; i < height.length - 1; i ++) {
            int min;

            if (height[leftIdx - 1] < height[rightIdx + 1]) {
                maxHeightL = Math.max(maxHeightL, height[leftIdx - 1]);
                min = maxHeightL;
                // 短板原则，左右两侧取小值
                int volume = min - height[leftIdx];
                if (volume > 0) {
                    answer += volume;
                }
                leftIdx ++;
            } else {
                maxHeightR = Math.max(maxHeightR, height[rightIdx + 1]);
                min = maxHeightR;
                // 短板原则，左右两侧取小值
                int volume = min - height[rightIdx];
                if (volume > 0) {
                    answer += volume;
                }
                rightIdx--;
            }


        }

        return answer;
    }

    /**
     * 动态规划进阶版：双指针之 左指针
     * 测试结果：几无差别
     *
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        int answer = 0;

        int maxHeightL = 0; // 左指针
        int[] maxHeightR = new int[height.length]; // 右侧最大的高度

        // 从最右侧算起
        for (int i = height.length - 2; i >= 0; i --) {
            maxHeightR[i] = Math.max(maxHeightR[i + 1], height[i + 1]);
        }

        for (int i = 1; i < height.length - 1; i ++) {
            // 更新左指针
            maxHeightL = Math.max(maxHeightL, height[i - 1]);
            // 短板原则，左右两侧取小值
            int min = Math.min(maxHeightL, maxHeightR[i]);
            int volume = min - height[i];
            if (volume > 0) {
                answer += volume;
            }
        }

        return answer;
    }

    /**
     * 动态规划
     *
     * @param height
     * @return
     */
    public int trap1(int[] height) {
        int answer = 0;

        int[] maxHeightL = new int[height.length]; // 左侧最大的高度
        int[] maxHeightR = new int[height.length]; // 右侧最大的高度

        for (int i = 1; i < height.length; i ++) {
            maxHeightL[i] = Math.max(maxHeightL[i - 1], height[i - 1]);
        }

        // 从最右侧算起
        for (int i = height.length - 2; i >= 0; i --) {
            maxHeightR[i] = Math.max(maxHeightR[i + 1], height[i + 1]);
        }

        for (int i = 1; i < height.length - 1; i ++) {
            // 短板原则，左右两侧取小值
            int min = Math.min(maxHeightL[i], maxHeightR[i]);
            int volume = min - height[i];
            if (volume > 0) {
                answer += volume;
            }
        }

        return answer;
    }

    /**
     * 好久没看了，已经想不起来当时的思路了
     *
     * @param height
     * @return
     */
    public int trap0(int[] height) {
        int answer = 0;

        Deque<Integer> stack = new LinkedList<Integer>();

            int leftHeight = 0, low = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > 0 && leftHeight == 0) {
                leftHeight = height[i];
            }

            while (! stack.isEmpty() ) {


                answer += Math.min(leftHeight, height[i]) * (stack.size() - 2);
            }

            if (leftHeight > 0) {
                stack.push(i);
            }
        }

        return answer;
    }
}
