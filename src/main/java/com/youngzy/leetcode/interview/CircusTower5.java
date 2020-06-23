package com.youngzy.leetcode.interview;


import java.util.Arrays;

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
public class CircusTower5 {
    /**
     * 基本思路不变，排序算法换一下
     *
     * @param height
     * @param weight
     * @return
     */
    public int bestSeqAtIndex(int[] height, int[] weight) {
        int[] answer = new int[height.length];

        // 初始化，至少一个
        for (int i = 0; i < height.length; i++) {
            answer[i] = 1;
        }

        // 选择排序
//        selectionSort(height, weight);
        // 冒泡排序
        // 插入排序
        // Shell 排序
        shellSort(height, weight);
        // 合并排序
        // 快速排序
//        quickSort(height, weight, 0, height.length - 1);

        dp(height, weight, 0, height.length - 1, answer);

//        Arrays.sort(answer);
        shellSort(answer, null);
        return answer[answer.length - 1];
//        int result = 0;
//        for (int i = 0; i < answer.length; i ++) {
//            if (result < answer[i]) {
//                result = answer[i];
//            }
//        }
//        return result;
    }

    private int dp(int[] height, int[] weight, int start, int end, int[] answer) {
        if (start == answer.length - 1) {
            return answer[start];
        }

        dp(height, weight, start + 1, answer.length - 1, answer);

        int idx = start + 1;
        while (idx <= end) {
            if (answer[start] < answer[idx] + 1
                    && height[start] < height[idx]
                    && weight[start] < weight[idx]) {
                answer[start] = answer[idx] + 1;
                // 不能break，只能一个个地去比较
//                break;
            }
            idx ++;
        }

        return answer[start];
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
                    swap(height, weight, i, j);
                }
            }
        }
    }

    private void swap(int[] height, int[] weight, int i, int j) {
        swap(height, i, j);
        swap(weight, i, j);
    }

    private void swap(int[] arr, int i, int j) {
        if (arr == null) {
            return;
        }
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private void quickSort(int[] height, int[] weight, int low, int high) {
        if (low >= high) {
            return;
        }

        int pivotIdx = partition(height, weight, low, high);
        quickSort(height, weight, low, pivotIdx - 1);
        quickSort(height, weight, pivotIdx + 1, high);
    }

    /**
     * 返回支点的正确位置
     *
     */
    private int partition(int[] height, int[] weight, int low, int high) {
        int pivot = height[low];

        int l = low, h = high;

        while (l < h) {
            while (height[l] <= pivot && l < h) {
                l++;
            }
            while (height[h] > pivot) {
                h--;
            }
            if (l < h) {
                swap(height, weight, l, h);
            }

        }

        swap(height, weight, low, h);

        return h;
    }

    private void shellSort(int[] height, int[] weight) {
        int increment = height.length / 2; // 随便选的，没有完美的选择

        while (increment > 0) {
            for (int start = 0; start < increment; start++) {
                insertionSort(height, weight, start, increment);
            }

            increment = increment / 2;
        }

    }

    private void insertionSort(int[] height, int[] weight, int start, int increment) {
        for (int i = start; i < height.length; i = i + increment) {
            // sublist(0) 有序
            // 从第二个元素开始比较
            for (int j = Math.min(height.length - 1, i + increment);
                 j - increment >= 0;
                 j = j - increment) {
                if (height[j] < height[j - increment]) {
                    swap(height, weight, j, j -increment);
                } else {
                    break;
                }

            }
        }

    }
}
