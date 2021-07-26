package com.youngzy.book.algs4.exercise;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author youngzy
 * @since 2021-07-24
 */
public class E010119 {
    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        for (int n = 0; n < 50; n++) {
            //95005 ms
//            StdOut.println(n + " " + fibonacci(n));

            // 3ms
            // 差别也太大了。。
//            StdOut.println(n + " " + fibonacci2(n));

            // 没有太大差别
            StdOut.println(n + " " + fibonacci3(n));
        }
        long end = System.currentTimeMillis();

        StdOut.println("cost(ms):" + (end - begin));
    }

    public static long fibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // 一个更好的实现：用数组保存已计算过的值
    static long fibonacci2(int n) {
        long[] arr = new long[n + 1]; // 0...n
        dp(arr, n);
        return arr[n];
    }

    private static void dp(long[] arr, int n) {
        if (arr[n] > 0 || n == 0) return;
        if (n == 1) {
            arr[1] = 1;
            return;
        }
        dp(arr, n - 1);
        dp(arr, n - 2);
        arr[n] = arr[n - 1] + arr[n - 2];
//        arr[n] = dp(arr, n - 1) + dp(arr, n - 2);
    }

    // dp有返回值
    static long fibonacci3(int n) {
        long[] arr = new long[n + 1]; // 0...n

        return dp3(arr, n);
    }

    private static long dp3(long[] arr, int n) {
        if (arr[n] > 0) return arr[n];

        if (n == 0) return 0;
        if (n == 1) {
            arr[1] = 1;
            return 1;
        }

        long n1 = dp3(arr, n - 1);
        long n2 = dp3(arr, n - 2);
        arr[n] = n1 + n2;
        return arr[n];
    }

}
