package com.youngzy.book.algs4.exercise;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author youngzy
 * @since 2021-07-24
 */
public class E010106 {
    /*
    f(0) = 0
    f(1) = 1
    f(2) = 2 // 这里笔算算错了。。
    f(i) = f(i-1) + f(i - 2)
     */
    public static void main(String[] args) {
        int f = 0;
        int g = 1;
        for (int i = 0; i <= 15; i++) {
            StdOut.println(f);
            f = f + g;
            g = f - g;
        }
    }

}
