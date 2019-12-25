package com.youngzy.leetcode.list;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle-ii/
 */
public class LinkedListCycleII {

    /**
     * 双指针（快慢指针）
     * 要构建快慢指针的2次相遇
     * 设链表共有a + b 个节点，a表示非环节点，b表示环节点
     *
     * 第1次相遇
     *      快指针走过 f 个节点
     *      慢指针走过 s 个节点
     *      f = 2s, f = s + nb （nb代表n个环节点）
     *      通过计算得出，f = 2nb, s = nb
     *
     * 第2次相遇
     *      对于慢指针，走过 a + nb 步，必然会回到环起点
     *      第一次相遇时，慢指针走了nb步。再走a步，慢指针将处于环入口
     *      如果此时再有个慢指针（步速一致）从起点开始走a步，它将第一次到达环起点，并和之前的慢指针相遇
     *      当前的节点，即为环起点
     *
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head; // 慢跑者
        ListNode fast = head; // 快跑者

        // 构建第一次相遇
        while (true) {
            if (fast == null || fast.next == null) {
                // 快跑者到达了终点，非环形
                return null;
            }
            slow = slow.next; // 每次一步
            fast = fast.next.next; // 每次两步
            if (slow == fast) {
                break;
            }
        }
        // 此时，慢跑者slow跑了nb步
        // 再来一个慢跑者slowB，从起点跑
        // 两个慢跑者相遇的点，即为环起点
        ListNode slowB = head;
        while (slow != slowB) {
            slow = slow.next;
            slowB = slowB.next;
        }

        return slowB;
    }

    /**
     * 用哈希表存放已访问过的节点
     * 如果发现表中已存在，则表示有环
     *
     * @param head
     * @return
     */
    public ListNode detectCycle1(ListNode head) {

        Set<ListNode> set = new HashSet<>();

        while (head != null) {
            if (! set.contains(head)) {
                set.add(head);
            } else {
                return head;
            }

            head = head.next;
        }

        return null;
    }
}
