package com.youngzy.leetcode.design;

import java.util.Stack;

/**
 * 基本实现
 */
public class SortedStack1 implements SortedStack {
    private final Stack<Integer> main, sub;

    public SortedStack1() {
        main = new Stack<>();
        sub = new Stack<>();
    }

    public void push(int val) {
        if (main.isEmpty()) {
            main.push(val);
        } else {
            while (!main.isEmpty() && main.peek() < val) {
                sub.push(main.pop());
            }
            main.push(val);
            while (!sub.isEmpty()) {
                main.push(sub.pop());
            }
        }

    }

    public void pop() {
        if (! isEmpty())
            main.pop();
    }

    public int peek() {
        if (isEmpty())
            return -1;
        return main.peek();
    }

    public boolean isEmpty() {
        return main.isEmpty();
    }
}
