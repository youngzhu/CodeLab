package com.youngzy.leetcode.design;

/**
 * 1381. 设计一个支持增量操作的栈
 *
 * 请你设计一个支持下述操作的栈。
 *
 * 实现自定义栈类 CustomStack ：
 *
 * CustomStack(int maxSize)：
 *      用 maxSize 初始化对象，maxSize 是栈中最多能容纳的元素数量，栈在增长到 maxSize 之后则不支持 push 操作。
 * void push(int x)：
 *      如果栈还未增长到 maxSize ，就将 x 添加到栈顶。
 * int pop()：
 *      弹出栈顶元素，并返回栈顶的值，或栈为空时返回 -1 。
 * void inc(int k, int val)：
 *      栈底的 k 个元素的值都增加 val 。如果栈中元素总数小于 k ，则栈中的所有元素都增加 val 。
 *
 * 示例：
 *
 * 输入：
 * ["CustomStack","push","push","pop","push","push","push","increment","increment","pop","pop","pop","pop"]
 * [[3],[1],[2],[],[2],[3],[4],[5,100],[2,100],[],[],[],[]]
 * 输出：
 * [null,null,null,2,null,null,null,null,null,103,202,201,-1]
 * 解释：
 * CustomStack customStack = new CustomStack(3); // 栈是空的 []
 * customStack.push(1);                          // 栈变为 [1]
 * customStack.push(2);                          // 栈变为 [1, 2]
 * customStack.pop();                            // 返回 2 --> 返回栈顶值 2，栈变为 [1]
 * customStack.push(2);                          // 栈变为 [1, 2]
 * customStack.push(3);                          // 栈变为 [1, 2, 3]
 * customStack.push(4);                          // 栈仍然是 [1, 2, 3]，不能添加其他元素使栈大小变为 4
 * customStack.increment(5, 100);                // 栈变为 [101, 102, 103]
 * customStack.increment(2, 100);                // 栈变为 [201, 202, 103]
 * customStack.pop();                            // 返回 103 --> 返回栈顶值 103，栈变为 [201, 202]
 * customStack.pop();                            // 返回 202 --> 返回栈顶值 202，栈变为 [201]
 * customStack.pop();                            // 返回 201 --> 返回栈顶值 201，栈变为 []
 * customStack.pop();                            // 返回 -1 --> 栈为空，返回 -1
 *  
 *
 * 提示：
 * 1 <= maxSize <= 1000
 * 1 <= x <= 1000
 * 1 <= k <= 1000
 * 0 <= val <= 100
 * 每种方法 increment，push 以及 pop 分别最多调用 1000 次
 *
 * 链接：https://leetcode-cn.com/problems/design-a-stack-with-increment-operation
 */
public class CustomStack {
    private final int maxSize;

    private Element top;
    private int size;

    public CustomStack(int maxSize) {
        this.maxSize = maxSize;
        this.size = 0;
    }

    public void push(int x) {
        if (size == maxSize) {
            return;
        }

        Element next = top;
        top = new Element(x, next);

        size ++;
    }

    public int pop() {
        if (size == 0) {
            return -1;
        }

        Element next = top.next;
        int value = top.value;
        top = next;
        size--;
        return value;
    }

    public void increment(int k, int val) {
        int counter = k - size;
        Element pointer = top;

        if (counter >= 0) {
            // 全栈增加
            while (pointer != null) {
                pointer.add(val);
                pointer = pointer.next;
            }
        } else {
            // 栈底 k 个元素增加
            while (pointer != null) {
                if (counter < 0) {
                    counter ++;
                } else {
                    pointer.add(val);
                }

                pointer = pointer.next;
            }

        }
    }

    private class Element {
        int value;
        Element next;

        Element(int value, Element next) {
            this.value = value;
            this.next = next;
        }

        void add(int value) {
            this.value += value;
        }
    }
}
