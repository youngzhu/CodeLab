package com.youngzy.leetcode.list;

/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
 */
public class ReverseLinkedListII {

    /**
     * 迭代
     *
     * 分为三段：反转段的前部分A，反转段B，反转段后部分C
     * 返回结果为：A + 反转后的B（B'）+ C
     *
     * 反转时：
     * next = cur.next
     * cur.next = pre
     * pre = cur
     * cur = next
     *
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head, int m, int n) {
        ListNode preHead = new ListNode(-1); // 假设的新列表的头

        preHead.next = head;

        ListNode pointer = preHead; // 用于遍历，不断向前推进

        for (int i = 1; i < m; i++) {
            pointer = pointer.next;
        }
        // 此时A段结束，A.tail=pointer

        // 需要反转的段，B段开始
        ListNode cur = pointer.next; // 反转段的head
        ListNode pre = null, next = null;
        for (int i = m ; i <=n ; i++) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 这时的next是C段的开始,C.head = next

        // 将反转后的列表整合到需要返回的列表中去
        // 即 A + B' + C

        // B.head = pointer.next
        // B'.tail = B.head
        // B'.tail.next = C.head = next
        pointer.next.next = next; // 将反转起点（pointer.next）的next指向反转段后面的部分
        // A.tail = pointer
        // A.tail.next = B'.head = pre
        pointer.next = pre; // 将反转段前一个节点的next指向反转后的头

        return preHead.next;
    }

}
