package com.youngzy.book.algs4.ch01;

import edu.princeton.cs.algs4.Counter;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Counter类的用例，模拟N次掷硬币
 * @author youngzy
 * @since 2021-07-31
 */
public class Flips {
    public static void main(String[] args) {
        int N = StdIn.readInt();
        Counter heads = new Counter("heads");
        Counter tails = new Counter("tails");
        for (int i = 0; i < N; i++) {
            if (StdRandom.bernoulli(0.5)) {
                heads.increment();
            } else {
                tails.increment();
            }
        }
        StdOut.println(heads);
        StdOut.println(tails);
        int d = heads.tally() - tails.tally();
        StdOut.println("delta: " + Math.abs(d));
    }
}
