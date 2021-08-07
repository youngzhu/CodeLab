package com.youngzy.book.algs4.ch01;

import edu.princeton.cs.algs4.Counter;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 模拟N次掷骰子的Counter对象的用例
 * @author youngzy
 * @since 2021-07-31
 */
public class Rolls {
    public static void main(String[] args) {
        int N = StdIn.readInt();
        int SIDES = 6;
        Counter[] rolls = new Counter[SIDES + 1];
        for (int i = 1; i <= SIDES; i++) {
            rolls[i] = new Counter(i + "'s");
        }

        for (int i = 0; i < N; i++) {
            int result = StdRandom.uniform(1, SIDES + 1);
            rolls[result].increment();
        }

        for (int i = 1; i <= SIDES; i++)
            StdOut.println(rolls[i]);
    }
}
