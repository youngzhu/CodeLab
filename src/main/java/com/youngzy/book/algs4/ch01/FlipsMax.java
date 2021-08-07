package com.youngzy.book.algs4.ch01;

import edu.princeton.cs.algs4.Counter;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 一个接受对象作为参数并将参数作为返回值的静态方法的例子
 * @author youngzy
 * @since 2021-07-31
 */
public class FlipsMax {
    public static Counter max(Counter x, Counter y) {
        if (x.tally() > y.tally())
            return x;
        else
            return y;
    }

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
        if (heads.tally() == tails.tally())
            StdOut.println("Tie");
        else
            StdOut.println(max(heads, tails) + " wins");
    }

}
