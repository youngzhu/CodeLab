package com.youngzy.book.algs4.exercise;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 从命令行获取3个整数值
 * 如果它们都相等，则打印equal，否则打印not equal
 *
 * @author youngzy
 * @since 2021-07-24
 */
public class E010103 {
    // 使用标准输入
    public static void main(String[] args) {
        int cnt = 3;
        int[] values = new int[cnt];
        while (cnt > 0 && !StdIn.isEmpty()) {
            values[--cnt] = StdIn.readInt();
        }

        boolean result = compare(cnt, values);

        if (result) {
            StdOut.println("equal");
        } else
            StdOut.println("not equal");

    }

    private static boolean compare(int cnt, int[] values) {
        if (cnt == 0) { // 三个值都输入了
            for (int i = 0; i < values.length; i++) {
                for (int j = i + 1; j < values.length; j++) {
                    if (values[i] != values[j])
                        return false;
                }
            }

            return true;
        }
        return false;
    }

}
