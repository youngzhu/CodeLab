package com.youngzy.book.algs4.ch01;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 定容栈
 *
 * @author youngzy
 * @since 2021-08-07
 */
public class FixedCapacityStackOfStrings {
    private String[] a; // stack entries
    private int N; // size

    public FixedCapacityStackOfStrings(int cap) {
        a = new String[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public boolean isFull() {
        return N == a.length;
    }

    public int size() {
        return N;
    }

    public void push(String item) {
        a[N++] = item;
    }

    public String pop() {
        return a[--N];
    }

    public static void main(String[] args) {
        FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(100);
        In in = new In("algs4/tobe.txt");

        while (in.hasNextLine()) {
            String item = in.readString();
            if (! "-".equals(item)) {
                stack.push(item);
            } else if (! stack.isEmpty()) {
                StdOut.print(stack.pop() + " ");
            }
        }
        StdOut.println("(" + stack.size() + " left on stack)");
    }
}
