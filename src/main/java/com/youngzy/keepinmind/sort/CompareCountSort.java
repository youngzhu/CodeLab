package com.youngzy.keepinmind.sort;

/**
 * 比较计数排序算法
 *
 * 时间复杂度：O(N^2)
 *
 * 效果比冒泡好
 */
public class CompareCountSort implements ISort {
    @Override
    public void sort(int[] a) {
        int[] copy = new int[a.length];
        for (int i = 0; i < a.length; i++)
            copy[i] = a[i];

        // 比较并计数（排名）
        int[] order = new int[a.length];

        for (int i = 0; i < a.length; i++) {
            int c = 0;
            for (int j = 0; j < a.length; j++) {
                if (i != j && a[i] >= a[j])
                    c ++;
            }
            order[i] = c;
        }

        for (int i = 0; i < a.length; i++) {
            int no = order[i];
            int val = copy[i];
            a[no] = val;
        }
    }
}
