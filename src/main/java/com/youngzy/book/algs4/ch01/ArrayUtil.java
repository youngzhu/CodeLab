package com.youngzy.book.algs4.ch01;

/**
 * @author youngzy
 * @since 2021-07-11
 */
public class ArrayUtil {
    /**
     * 找出数组中最大的元素
     *
     * @param a
     * @return
     */
    public static int findMax(int[] a) {
        int max = a[0];
        for (int i = 1; i < a.length; i++)
            if (a[i] > max) {
                max = a[i];
            }
        return max;
    }

    /**
     * 计算数组元素的平均值
     *
     * @param a
     * @return
     */
    public static double avg(int[] a) {
        int N = a.length;
        double sum = 0;
        for (int i = 0; i < N; i++) {
            sum += a[i];
        }
        return sum / N;
    }

    /**
     * 复制数组
     *
     * @param a
     * @return
     */
    public static int[] copy(int[] a) {
        int N = a.length;
        int[] b = new int[N];
        for (int i = 0; i < N; i++) {
            b[i] = a[i];
        }
        return b;
    }

    /**
     * 颠倒数组元素的顺序
     *
     * @param a
     */
    public static void reverse(int[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int temp = a[i];
            a[i] = a[N - 1 - i];
            a[N - 1 - i] = temp;
        }
    }

    /**
     * 矩阵相乘
     * c[][] = a[][] * b[][]
     *
     * @param a
     * @param b
     * @return
     */
    public static int[][] multiply(int[][] a, int[][] b) {
        int N = a.length;
        int[][] c = new int[N][];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                for (int k = 0; k < N; k++)
                    c[i][j] += a[i][k]*b[k][j];
        return c;
    }
}
