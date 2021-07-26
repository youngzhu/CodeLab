package com.youngzy.book.algs4.exercise;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author youngzy
 * @since 2021-07-24
 */
public class E010112 {
    public static void main(String[] args) {
        int[] a = new int[10];
        for (int i = 0; i < 10; i++)
            a[i] = 9 - i;// 9...0
        for (int i = 0; i < 10; i++)
            a[i] = a[a[i]];
        for (int i = 0; i < 10; i++)
            System.out.print(a[i] + " "); // 0 1 2 3 4 4 3 2 1 0
        // 自己算错了，以为是0...9
    }
}
