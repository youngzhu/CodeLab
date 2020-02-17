package com.youngzy.leetcode.list;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。
 * 其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 */
public class AddTwoNumbers {

    /**
     * 优化 while
     *
     * 没啥区别。。
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(0);
        ListNode pointer = ans; // 移动的指针
        ListNode node;

        int carry = 0; // 进位
        int sum = 0;

        // 要考虑2个链表不一样长的情况
        int v1, v2;
        while (l1 != null || l2 != null) {
            v1 = null == l1 ? 0 : l1.val;
            v2 = null == l2 ? 0 : l2.val;

            sum = v1 + v2 + carry;

            carry = sum / 10;
            node = new ListNode(sum % 10);
            pointer.next = node;
            pointer = pointer.next;

            l1 = null == l1 ? null : l1.next;
            l2 = null == l2 ? null : l2.next;
        }

        // 最后一位要进位，超出原列表的长度
        // 之前没考虑到
        if (carry > 0) {
            pointer.next = new ListNode(carry);
        }

        return ans.next;
    }

    public ListNode addTwoNumbers0(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(0);
        ListNode pointer = ans; // 移动的指针
        ListNode node;

        int overflow = 0; // 进位
        int sum = 0;

        // 要考虑2个链表不一样长的情况
        while (l1 != null && l2 != null) {
            sum = l1.val + l2.val + overflow;

            overflow = sum / 10;
            node = new ListNode(sum % 10);
            pointer.next = node;
            pointer = node;

            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            sum = l1.val + overflow;

            overflow = sum / 10;
            node = new ListNode(sum % 10);
            pointer.next = node;
            pointer = node;

            l1 = l1.next;
        }

        while (l2 != null) {
            sum = l2.val + overflow;

            overflow = sum / 10;
            node = new ListNode(sum % 10);
            pointer.next = node;
            pointer = node;

            l2 = l2.next;
        }

        // 最后一位要进位，超出原列表的长度
        // 之前没考虑到
        if (overflow > 0) {
            pointer.next = new ListNode(overflow);
        }

        return ans.next;
    }
}
