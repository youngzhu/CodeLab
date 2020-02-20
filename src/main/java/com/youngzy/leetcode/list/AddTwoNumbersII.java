package com.youngzy.leetcode.list;

/**
 * 给定两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。
 * 它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 * 进阶:
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 *
 * 示例:
 * 输入: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出: 7 -> 8 -> 0 -> 7
 *
 * 链接：https://leetcode-cn.com/problems/add-two-numbers-ii
 */
public class AddTwoNumbersII {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(0);
        ListNode cur = ans;

        int carry = add(cur, l1, l2);

        if (carry > 0) {
            // 处理最大长度溢出的情况
            // 如 [1],[9]
            ListNode ln = new ListNode(carry);
            ln.next = ans.next;
            ans.next = ln;
        }

        return ans.next;
    }

    /**
     * 递归
     *
     * 因为进位carry 无法像对象一样在方法中传递，
     * 所以必须return
     *
     * @param cur
     * @param l1
     * @param l2
     * @return
     */
    private int add(ListNode cur, ListNode l1, ListNode l2) {

        int carry = 0; // 进位
        int sum;

        if (l1.next == null && l2.next == null) {
            // 都到了最后一位
            sum = l1.val + l2.val + carry;
            carry = sum / 10;

            cur.next = new ListNode(sum % 10);
            return carry;
        }

        int v1 = l1.val;
        int v2 = l2.val;

        if (l1.next == null) {
            l1 = l1;
            v1 = 0;
        } else {
            l1 = l1.next;
        }
        if (l2.next == null) {
            l2 = l2;
            v2 = 0;
        } else {
            l2 = l2.next;
        }

        cur.next = new ListNode(v1 + v2);
        cur = cur.next;
        carry = add(cur, l1, l2);

        // 重新处理进位
        sum = cur.val + carry;
        carry = sum / 10;
        cur.val = sum % 10;

        return carry;
    }

}
