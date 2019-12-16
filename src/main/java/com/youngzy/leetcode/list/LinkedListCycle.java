package com.youngzy.leetcode.list;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个链表，判断链表中是否有环。
 *
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle/
 */
public class LinkedListCycle {

    /**
     * 双指针（快慢指针）
     * 想象两个运动员在赛跑（假设赛程足够长），
     * 如果是在环形跑道上，两者一定会再次相遇；
     * 如果不是环形的，速度快者先到底终点
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {

        if (head == null || head.next == null) {
            // 如果列表为空或只有一个节点，则是非环
            return false;
        }

        ListNode slow = head; // 慢跑者
        ListNode fast = head.next; // 快跑者

        while (slow != fast) {
            if (fast == null || fast.next == null) {
                // 快跑者到达了终点，非环形
                return false;
            }
            slow = slow.next; // 每次一步
            fast = fast.next.next; // 每次两步
        }

        // slow == fast，两者相遇，环形
        return true;
    }

    /**
     * 用哈希表存放已访问过的节点
     * 如果发现表中已存在，则表示有环
     *
     * @param head
     * @return
     */
    public boolean hasCycle1(ListNode head) {

        Set<ListNode> set = new HashSet<>();

        while (head != null) {
            if (! set.contains(head)) {
                set.add(head);
            } else {
                return true;
            }

            head = head.next;
        }

        return false;
    }
}
