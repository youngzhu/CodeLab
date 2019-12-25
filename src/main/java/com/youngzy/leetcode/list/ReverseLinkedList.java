package com.youngzy.leetcode.list;

/**
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class ReverseLinkedList {

    /**
     * 迭代
     *
     * 遍历列表：
     * next = cur.next
     * cur.next = pre
     * pre = cur
     * cur = next
     *
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head, next = null;

        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    /**
     * 递归
     *  可用 1->2->3 演示代码路径，帮助理解
     *
     * @param head
     * @return
     */
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode node = reverseList(head.next);
        // 将箭头反转
        head.next.next = head;
        // 主要是反转后，原列表的第一个元素要指向null
        head.next = null;

        return node;
    }
}
