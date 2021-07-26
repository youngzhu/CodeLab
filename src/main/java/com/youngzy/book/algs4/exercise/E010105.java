package com.youngzy.book.algs4.exercise;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 如果double类型的变量x，y都严格位于0到1之间则打印true
 * 否则打印false
 *
 * @author youngzy
 * @since 2021-07-24
 */
public class E010105 {
    // 使用标准输入
    public static void main(String[] args) {
        double x, y;
        x = 1.001;
        y = 0;
        StdOut.println(inRange(x) && inRange(y));

        x = 1.0;
        y = 0;
        StdOut.println(inRange(x) && inRange(y));

        x = 1.001;
        y = 0.99;
        StdOut.println(inRange(x) && inRange(y));

    }

    private static boolean inRange(double x) {
        return x >= 0 && x <= 1;
    }


}
