package com.youngzy.stackskills.dsa.stack;

import java.util.Stack;

/**
 * 查找最小的元素
 */
public class MinimumStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinimumStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int num) {
        int min = num;

        if (! minStack.isEmpty()) {
            if (min > minStack.peek()) {
                min = minStack.peek();
            }
        }

        stack.push(num);
        minStack.push(min);
    }

    public int getMinimum() {
        return minStack.peek();
    }
}
