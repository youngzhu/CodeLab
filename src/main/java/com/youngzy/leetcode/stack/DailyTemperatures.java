package com.youngzy.leetcode.stack;

import java.util.*;

/**
 * 请根据每日 气温 列表，重新生成一个列表。
 * 对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
 * 你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 *
 * 链接：https://leetcode-cn.com/problems/daily-temperatures
 */
public class DailyTemperatures {

    /**
     * 新学一个名次：单调栈
     *
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        int[] answer = new int[T.length];

        // stack vs linkedlist : 24:18ms
        // 栈，从上到下，温度递增
//        Stack<Integer> stack = new Stack<>();
        Deque<Integer> stack = new LinkedList<Integer>();

        /*
        for (int i = 0; i < T.length; i++) {
            // 空栈，直接入栈
            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }
            if (T[stack.peek()] > T[i]) {
                // 当前温度小于栈顶温度，直接入栈
                stack.push(i);
            } else {
                while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                    // 当前温度大于栈顶温度
                    // 需要将小于当前温度的所有栈内温度出栈
                    int preIdx = stack.pop();
                    answer[preIdx] = i - preIdx;
                }
                stack.push(i);
            }
        }
         */
        // 简化
        // 简化后时间更少了，18 vs 15
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                int preIdx = stack.pop();
                answer[preIdx] = i - preIdx;
            }
            stack.push(i);
        }

        return answer;
    }

    /**
     * 试试能不能优化，我觉得我的想法没啥问题啊
     *
     * 弃。。。
     *
     * @param T
     * @return
     */
    public int[] dailyTemperatures2(int[] T) {
        int[] answer = new int[T.length];

        List<Integer>[] occurs = new List[71];
        for (int i = 0; i < 71; i ++) {
            occurs[i] = new ArrayList<>();
        }

        for (int i = 0; i < T.length; i++) {
            int temp = T[i];
            occurs[temp - 30].add(i);
        }

        // 排序
        for (int i = 0; i < occurs.length; i ++) {
            List list = occurs[i];
            if (list.isEmpty()) {
                list = null;
            } else {
                Collections.sort(list);
            }
            occurs[i] = list;
        }

        for (int i = 0; i < T.length; i++) {
            int temp = T[i];

            int minPos = T.length; // 最近的高温的下标，默认最大值
            for (int warmerTemp = temp + 1 - 30; warmerTemp < occurs.length; warmerTemp ++) {
                if (occurs[warmerTemp] == null) {
                    continue;
                } else {
                    System.out.println(warmerTemp + "," + occurs[warmerTemp].size());
                    for (int j : occurs[warmerTemp]) {
                        if (j >= minPos) {
                            break;
                        }
                        if (j > i) {
                            minPos = j;
                        }

                    }
                }
            }
            answer[i] = minPos == T.length ? 0 : minPos - i;
        }

        return answer;
    }

    /**
     * 超时了，比第一个还差
     * 
     * @param T
     * @return
     */
    public int[] dailyTemperatures1(int[] T) {
        int[] answer = new int[T.length];

        List<Integer>[] occurs = new List[71];
        for (int i = 0; i < 71; i ++) {
            occurs[i] = new ArrayList<>();
        }

        for (int i = 0; i < T.length; i++) {
            int temp = T[i];
            occurs[temp - 30].add(i);
        }

        // 排序
        for (int i = 0; i < occurs.length; i ++) {
            List list = occurs[i];
            if (list.isEmpty()) {
                list = null;
            } else {
                Collections.sort(list);
            }
            occurs[i] = list;
        }

        for (int i = 0; i < T.length; i++) {
            int temp = T[i];

            int min = T.length;
            for (int warmerTemp = temp + 1 - 30; warmerTemp < occurs.length; warmerTemp ++) {
                if (occurs[warmerTemp] == null) {
                    continue;
                } else {
                    for (int j : occurs[warmerTemp]) {
                        int diff = j - i;
                        if (diff > 0 && diff < min) {
                            min = diff;
                        }

                        if (min == 1) {
                            break;
                        }
                    }
                }
            }
            answer[i] = min == T.length ? 0 : min;
        }

        return answer;
    }

    /**
     * 最基本的算法，耗时 700ms+
     * @param T
     * @return
     */
    public int[] dailyTemperatures0(int[] T) {
        int[] answer = new int[T.length];

        for (int i = 0; i < T.length; i++) {
            int temp = T[i];
            int days = 0;
            for (int j = i + 1; j < T.length; j++) {
                if (T[j] > temp) {
                    days = j - i;
                    break;
                }
            }

            answer[i] = days;
        }

        return answer;
    }
}
