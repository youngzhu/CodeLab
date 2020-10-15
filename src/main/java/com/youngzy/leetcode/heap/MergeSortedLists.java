package com.youngzy.leetcode.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * 示例 1：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 * <p>
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：lists = [[]]
 * 输出：[]
 * <p>
 * 提示：
 * <p>
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 * <p>
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 */
public class MergeSortedLists {
    PriorityQueue<ListNode> heap;

    public MergeSortedLists() {
        // PriorityQueue 这个类是最大堆，即最大值在顶部
        // 但是给出的列表是从小到大排列的
        // 所以这里需要转换一下，最小值放在顶部
        heap = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                // val最小的在堆顶
                return o1.val - o2.val;
            }
        });
    }

    public ListNode mergeKLists(ListNode[] lists) {
        heap.clear();

        for (ListNode node : lists) {
            if (node != null) {
                heap.add(node);
            }
        }

        ListNode preHead = new ListNode();
        ListNode tail = preHead;
        while (!heap.isEmpty()) {
            ListNode top = heap.poll();
            if (top.next != null) {
                heap.add(top.next);
            }

            ListNode newHead = new ListNode(top.val);
            if (preHead == tail) {
                preHead.next = newHead;
            } else {
                tail.next = newHead;
            }
            tail = newHead;
        }

        return preHead.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
