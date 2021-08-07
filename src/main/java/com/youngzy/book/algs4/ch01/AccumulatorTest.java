package com.youngzy.book.algs4.ch01;

import edu.princeton.cs.algs4.Accumulator;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 累加器
 *
 * @author youngzy
 * @since 2021-08-01
 */
public class AccumulatorTest {
    public static void main(String[] args) {
        int N = StdIn.readInt();
        Accumulator a = new Accumulator();
        for (int i = 0; i < N; i++)
            a.addDataValue(StdRandom.uniform());
        StdOut.println(a);
    }
}
