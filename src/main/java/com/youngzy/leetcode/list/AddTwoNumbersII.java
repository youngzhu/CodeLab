package com.youngzy.leetcode.list;

import java.time.Clock;
import java.util.List;

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
        ListNode ans = new ListNode(-1);
        ListNode cur = ans;

        // 将补全的列表
        // 有个伪头
        ListNode ln1 = new ListNode(0);
        ListNode ln2 = new ListNode(0);
        ln1.next = l1;
        ln2.next = l2;

        fillWithZero(ln1, ln2);

        // 用补零后的链表去递归
        int carry = add(cur, ln1.next, ln2.next);

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
     * 给位数较少的数据补零
     * 即 最终l1，l2 一样长
     *
     * @param l1
     * @param l2
     */
    private void fillWithZero(ListNode l1, ListNode l2) {
        ListNode p1 = l1, p2 = l2; // 移动指针
        ListNode zero1 = null, zero2 = null;

        while (p1 != null && p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        while (p1 != null) {
            // 1 没结束，给 2 补零
            insertZero(l2);
            p1 = p1.next;
        }

        while (p2 != null) {
            insertZero(l1);
            p2 = p2.next;
        }

    }

    private void insertZero(ListNode ln) {
        ListNode zero = new ListNode(0);
        zero.next = ln.next;
        ln.next = zero;
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

        if (l1 == null || l2 == null) {
            return 0;
        }

        // 先占个位
        // val 就是对应位置上的2值相加，后面处理进位问题
        cur.next = new ListNode(l1.val + l2.val);
        cur = cur.next;
        l1 = l1.next;
        l2 = l2.next;
        carry = add(cur, l1, l2);

        // 处理进位
        sum = cur.val + carry;
        carry = sum / 10;
        cur.val = sum % 10;

        return carry;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(1);

        ListNode l2 = new ListNode(9);

        AddTwoNumbersII atn = new AddTwoNumbersII();
        atn.addTwoNumbers(l1, l2);

        System.out.println();
    }

}
