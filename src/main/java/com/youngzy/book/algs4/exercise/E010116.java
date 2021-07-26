package com.youngzy.book.algs4.exercise;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author youngzy
 * @since 2021-07-24
 */
public class E010116 {
    public static void main(String[] args) {
        StdOut.print(exR1(6)); //311361142246 , 又算错了。。
    }

    public static String exR1(int n) {
        if (n <= 0)
            return "";
        return exR1(n - 3) + n + exR1(n - 2) +n;
    }
}
