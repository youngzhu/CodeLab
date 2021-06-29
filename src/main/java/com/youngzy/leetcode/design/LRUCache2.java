package com.youngzy.leetcode.design;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/lru-cache/solution/lru-ce-lue-xiang-jie-he-shi-xian-by-labuladong/
 * 参考了另一个题解，领悟到我没有必要自己去实现一个Map啊，可以组合使用
 *
 * 基本思路
 * 题目的要求：查找快，插入快，删除快
 *
 * 已知的数据结构
 * Map：查找快
 * 链表：插入快
 * 双向链表：插入快，删除快
 *
 * 所以，应该用 Map+双向链表 实现
 */
public class LRUCache2 {
    private final int capacity;

    Map<Integer, Node> map;
    private DoublyLinkedList cache;

    public LRUCache2(int capacity) {
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
                afterNodeRemoval(head);
            }

            node = new Node(key, value, null);
            cache.add(node);
        } else {
            node.value = value;
        }
        afterNodeAccess(node);

        map.put(key, node);
    }

    // https://leetcode-cn.com/problems/lru-cache/solution/yuan-yu-linkedhashmapyuan-ma-by-jeromememory/
    /**
     * 访问过的节点移到末尾
     *
     * @param node
     */
    private void afterNodeAccess(Node node) {
        Node last = cache.tail.before;

        if (last != node) { // 要移动的节点不是尾节点
            Node p = node, b = p.before, a = p.after; // b <=> p <=> a

            b.after = a;
            a.before = b;

            last.after = p;
            p.before = last;
            p.after = cache.tail;
            cache.tail.before = p;
            /*

            p.after = null; // b <=> p <- a

            if (b == null) { // 如果 p 是头
                cache.head = a; // 现在 a 成了头
            } else {
                b.after = a; // b -> a
            }

            // a 还是可能为 null 的
            // 之前自作聪明地以为 node 不是最后一个，所以必定有 next
            if (a != null) {
                a.before = b; // b <- a
            } else {
                last = b;
            }

            if (last == null) { // 空链表
                cache.head = p; // p 是第一个元素，且为头
            } else {
                // last <=> p
                p.before = last;
                last.after = p;
            }

            cache.tail = p;
*/
        }
    }

    // 删除元素时，修改双向链表
    void afterNodeRemoval(Node node) {
        Node p = node;
        Node b = p.before, a = p.after; // b <=> p <=> a

        p.before = p.after = null; // p 的前后节点置空

        b.after = a;
        a.before = b;

        /*
        if (b == null) { // p 为头节点
            cache.head = a; // a 变成头
        } else {
            b.after = a; // b -> a
        }

        if (a == null) { // p 是尾节点
            cache.tail = b; // b 变成尾
        } else {
            a.before = b; // b <- a
        }
         */

    }

    void afterNodeInsertion(boolean evict) { // possibly remove eldest
//        LinkedHashMap.Entry<K,V> first;
//        if (evict && (first = head) != null && removeEldestEntry(first)) {
//            K key = first.key;
//            removeNode(hash(key), key, null, false, true);
//        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Node node = cache.head.after;
        while (node!= null && node != cache.tail) {
            sb.append(node.key)
                    .append("-")
                    .append(node.value)
                    .append(";");

            node = node.after;
        }

        return sb.toString();
    }

    class DoublyLinkedList {
        transient Node head; // eldest
        transient Node tail; // youngest，新增的放尾部

        final Node HEAD = new Node(Integer.MIN_VALUE, Integer.MIN_VALUE, null);
        final Node TAIL = new Node(Integer.MIN_VALUE, Integer.MIN_VALUE, null);

        transient int size;

        public DoublyLinkedList() {
            size= 0;
            head = HEAD;
            tail = TAIL;
            head.after = tail;
            tail.before = head;
        }

        public Node removeHead() {
            Node rm = head.after;
            head.after = rm.after;
            -- size;
            return rm;
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

        Node(int key, int value, Node after) {
            this.key = key;
            this.value = value;
            this.after = after;
        }

    }
}
