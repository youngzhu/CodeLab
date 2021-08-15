package com.youngzy.book.algs4.ch01;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 范型定容栈
 *
 * @author youngzy
 * @since 2021-08-07
 */
public class FixedCapacityStack<Item> {
    private Item[] a; // stack entries
    private int N; // size

    public FixedCapacityStack(int cap) {
        a = (Item[]) new Object[cap];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void push(Item item) {
        a[N++] = item;
    }

    public Item pop() {
        return a[--N];
    }

    public static void main(String[] args) {
        FixedCapacityStack<String> stack = new FixedCapacityStack<String>(100);
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
