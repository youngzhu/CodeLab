package com.youngzy.book.algs4.ch01;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Stack 的用例
 *
 * @author youngzy
 * @since 2021-08-07
 */
public class Reverse {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        while (!StdIn.isEmpty())
            stack.push(StdIn.readInt());

        for (int i : stack)
            StdOut.println(i);
    }
}
