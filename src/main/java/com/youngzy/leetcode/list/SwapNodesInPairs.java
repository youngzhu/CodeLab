package com.youngzy.leetcode.list;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *  
 * 示例:
 *
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 *
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 */
public class SwapNodesInPairs {


    /**
     * 效率上跟递归没差别
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode preHead = new ListNode(-1);
        preHead.next = head;

        ListNode pointer = preHead;

        while (pointer.next != null && pointer.next.next != null) {
            // 保证是成对的

            ListNode one = pointer.next;
            ListNode two = one.next;

            pointer.next = two;
            one.next = two.next;
            two.next = one;

            pointer = one;
        }

        return preHead.next;
    }

    /**
     * 递归
     *
     * @param head
     * @return
     */
    public ListNode swapPairs1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 每次递归完成一次两两交换
        ListNode one = head;
        ListNode two = head.next;

        one.next = swapPairs(two.next);
        two.next = one;

        return two;
    }
}
