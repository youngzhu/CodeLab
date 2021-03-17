package com.youngzy.leetcode.design;

import java.util.Stack;

/**
 * 优化：
 * 如果 sub.peek < val < main.peek
 * 那么可以直接将 val 先放在 sub中，可避免部分移动
 *
 * 特点：两个栈可能都是非空的
 *
 * 时间效率提高了十多倍，空间没变化
 * 22 ms VS 222 ms
 *
 */
public class SortedStack2 implements SortedStack {
    private final Stack<Integer> main, sub;

    public SortedStack2() {
        main = new Stack<>();
        sub = new Stack<>();
    }

    public void push(int val) {
        // main sub 空或非空的4种情况
        if (main.isEmpty() && sub.isEmpty()) {
            // 1 空-空
            main.push(val);
        } else if (! main.isEmpty() && sub.isEmpty()) {
            // 2 非空-空
            push2(val);
        } else if (main.isEmpty() && ! sub.isEmpty()) {
            // 3 空-非空
            // 不会有这种情形
        } else {
            // 4 非空-非空
            push4(val);
        }

    }

    /**
     * 非空-非空
     *
     * @param val
     */
    private void push4(int val) {
        if (val > main.peek()) {
            transferAndPush(main, sub, val);
        } else if (val < sub.peek()) {
            transferAndPush2(sub, main, val);
        } else {
            sub.push(val);
        }
    }

    /**
     * 非空-空
     * v < m(t) , m.push
     * else m -> s, m.push
     *
     * @param val
     */
    private void push2(int val) {
        if (val < main.peek()) {
            main.push(val); // 可保证在转移过程中main不空
        } else {
            transferAndPush(main, sub, val);
        }
    }

    /**
     * stack1 (上到下）升序
     * Stack2 降序
     * 1. 将stack1的数据转移到stack2，直到 stack1.top >= val
     * 2. stack1.push(val)
     *
     * @param stack1
     * @param stack2
     * @param val
     */
    private void transferAndPush(Stack<Integer> stack1, Stack<Integer> stack2, int val) {
        while (! stack1.isEmpty() && stack1.peek() < val) {
            stack2.push(stack1.pop());
        }
        stack1.push(val);
    }

    /**
     * stack1 (上到下）降序
     * Stack2 升序
     * 1. 将stack1的数据转移到stack2，直到 stack1.top < val
     * 2. stack1.push(val)
     *
     * @param stack1
     * @param stack2
     * @param val
     */
    private void transferAndPush2(Stack<Integer> stack1, Stack<Integer> stack2, int val) {
        while (! stack1.isEmpty() && stack1.peek() >= val) {
            stack2.push(stack1.pop());
        }
        stack1.push(val);
    }

    public void pop() {
        subToMain();
        if (! main.isEmpty())
            main.pop();
    }

    public int peek() {
        subToMain();
        if (main.isEmpty())
            return -1;
        else
            return main.peek();
    }

    /**
     * 将sub的数据（如果有的话），转移到main
     */
    private void subToMain() {
        while (! sub.isEmpty()) {
            main.push(sub.pop());
        }
    }

    public boolean isEmpty() {
        return main.isEmpty() && sub.isEmpty();
    }
}
