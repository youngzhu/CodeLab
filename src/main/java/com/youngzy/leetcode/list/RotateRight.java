package com.youngzy.leetcode.list;

/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 示例 2:
 *
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 *
 * 链接：https://leetcode-cn.com/problems/rotate-list
 */
public class RotateRight {

    /**
     * 对k取模可以减少循环
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        // 计算列表的长度
        // 是为了下面的for循环取模
        int len = 0;
        ListNode pointer = head; // 移动指针
        ListNode pre = null; // 当前指针（pointer）的前一个节点

        while (pointer != null) {
            len ++;
            pointer = pointer.next;
        }

        pointer = head; // 指针重新指向表头

        // 如果不取模，k很大时将超时
        for (int i = 0; i < k % len; i++) {

            // 将指针移到最后一个节点
            while (pointer.next != null) {
                pre = pointer;
                pointer = pointer.next;
            }

            // 首尾相连，形成伪环
            pointer.next = head;
            // 原先的列表尾部变成列表头
            head = pointer;
            // 倒数第二个节点变成列表尾部，指向null
            pre.next = null;
        }

        return pointer;
    }

    /**
     * 初版
     * 可以执行。
     * 但是k很大时，超出了时间限制
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight0(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pointer = head;
        ListNode pre = null;

        for (int i = 0; i < k; i++) {

            while (pointer.next != null) {
                pre = pointer;
                pointer = pointer.next;

            }

            pointer.next = head;
            pre.next = null;
            head = pointer;
        }

        return pointer;
    }
}
