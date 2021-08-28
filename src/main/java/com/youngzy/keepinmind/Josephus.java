package com.youngzy.keepinmind;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 优雅的约瑟夫环
 *
 * 原地址：https://algs4.cs.princeton.edu/13stacks/Josephus.java
 *
 * @author youngzy
 * @since 2021-08-28
 */
public class Josephus {
    /**
     *
     * @param m - 第m个退出
     * @param n - 总人数
     * @return
     */
    public String solve(int m, int n) {
        // 队列，先进先出
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            queue.offer(i);
        }

        StringBuilder result = new StringBuilder();
        while (! queue.isEmpty()) {
            for (int i = 0; i < m - 1; i++) {
                // 形成环
                queue.offer(queue.poll());
            }
            result.append(queue.poll() + " ");
        }
        return result.toString();
    }
}
