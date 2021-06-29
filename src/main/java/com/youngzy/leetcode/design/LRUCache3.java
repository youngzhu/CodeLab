package com.youngzy.leetcode.design;

import java.util.HashMap;
import java.util.Map;

/**
 * 在2的基础上优化一下代码
 * 把afterNodeRemoval移到了双向链表类里 === 效率上没啥变化
 */
public class LRUCache3 {
    private final int capacity;

    private Map<Integer, Node> map;
    private DoublyLinkedList cache;

    public LRUCache3(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        cache = new DoublyLinkedList();
    }

    public int get(Object key) {
        Node node = map.get(key);
        if (node == null)
            return -1;
        else {
            afterNodeAccess(node);
            return node.value;
        }
    }

    public void put(int key, int value) {

        Node node = map.get(key);
        if (node == null) {
            if (capacity == cache.size) { // 只有新的key，且满了才需要删
                Node head = cache.removeHead();
                map.remove(head.key);
//                afterNodeRemoval(head);
            }

            node = new Node(key, value, null);
            cache.add(node);
        } else {
            node.value = value;
        }
        afterNodeAccess(node);

        map.put(key, node);
    }

    /**
     * 访问过的节点移到末尾
     *
     * @param node
     */
    private void afterNodeAccess(Node node) {
        Node last = cache.tail.before;

        if (last != node) { // 要移动的节点不是尾节点
            Node p = node, b = p.before, a = p.after; // b <=> p <=> a

            // 将 p 的前后节点连起来
            b.after = a;
            a.before = b;

            // 将 p 和原来的最后一个节点双向关联
            last.after = p;
            p.before = last;

            // 将 p 和tail双向关联
            p.after = cache.tail;
            cache.tail.before = p;
        }
    }

    class DoublyLinkedList {
        transient Node head; // eldest
        transient Node tail; // youngest，新增的放尾部

        transient int size;

        public DoublyLinkedList() {
            size= 0;
            head = new Node(-1); // 相当于火车头和火车尾，不算真正的车厢
            tail = new Node(-1);
            head.after = tail;
            tail.before = head;
        }

        public Node removeHead() {
            Node rm = head.after;
            head.after = rm.after;
            afterNodeRemoval(rm);
            -- size;
            return rm;
        }

        // 删除元素时，修改双向链表
        void afterNodeRemoval(Node node) {
            Node p = node;
            Node b = p.before, a = p.after; // b <=> p <=> a

            p.before = p.after = null; // p 的前后节点置空

            b.after = a;
            a.before = b;

        }

        public void add(Node node) {
            Node before = tail.before;
            node.before = before;
            node.after = tail;

            ++ size;
        }

    }

    static class Node {
        final int key;
        int value;

        Node before, after;

        public Node(int key) {
            this.key = key;
        }

        Node(int key, int value, Node after) {
            this.key = key;
            this.value = value;
            this.after = after;
        }

    }
}
