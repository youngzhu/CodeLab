package com.youngzy.book.algs4.exercise;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author youngzy
 * @since 2021-07-24
 */
public class E010204 {
    public static void main(String[] args) {
        String s1 = "hello";
        String s2 = s1;
        s1 = "world";
        StdOut.println(s1); // world
        StdOut.println(s2); // hello
    }

}
