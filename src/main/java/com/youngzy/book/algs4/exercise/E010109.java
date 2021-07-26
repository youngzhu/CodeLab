package com.youngzy.book.algs4.exercise;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author youngzy
 * @since 2021-07-24
 */
public class E010109 {
    public static void main(String[] args) {
        String s = toBinaryString(1);
        StdOut.println(s);

        s = toBinaryString(2);
        StdOut.println(s);

        s = toBinaryString(8);
        StdOut.println(s);
    }

    private static String toBinaryString(int n) {
//        Integer.toBinaryString(n);
        String s = "";
        for (int i = n; i > 0; i /= 2)
            s = (i % 2) + s;
        return s;
    }

}
