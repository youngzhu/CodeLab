package com.youngzy.keepinmind.sort;

/**
 * 插入排序
 *
 * 时间复杂度：O(N^2)
 */
public class InsertSort implements ISort {
    /**
     * 需要排序p（1...n-1）次，a[p]之前的是有序的 --> 第一个for
     * 每次排序就是给a[p]在0-p中找到正确的位置 --> 第二个for
     *
     * @param a
     */
    @Override
    public void sort(int[] a) {
        for (int p = 1; p < a.length; p++) {
            int tmp = a[p];
            // 关于 i 的选择：i=p 还是 i=p-1？
            // 假设a[p]就在正确的位置上，那么a[i]=a[p]
            // 所以，i=p
            int i = p;
            for (; i > 0 && tmp < a[i - 1]; i--) {
                a[i] = a[i - 1]; // 比 a[p] 大的都往后移一位
            }
            a[i] = tmp; // 将 a[p] 插入到正确的位置。插入法由此得名
        }

    }
}
