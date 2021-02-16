package com.youngzy.book.concurrency.ch15;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 15-7 Michael-Scott(Michael and Scott, 1996) 非阻塞算法中的插入算法
 */
public class LinkedQueue<E> {
    private static class Node<E> {
        final E item;
        final AtomicReference<Node<E>> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<Node<E>>(next);
        }
    }

    // 哑节点
    private final Node<E> dummy = new Node<>(null, null);
    // 初始化时，头节点和尾节点都指向哑节点
    private final AtomicReference<Node<E>> head =
            new AtomicReference<>(dummy);
    // 尾节点，要么指向最后一个元素，要么（当有操作正在更新时）指向倒数第二个元素
    private final AtomicReference<Node<E>> tail =
            new AtomicReference<>(dummy);

    public boolean put(E item) {
        Node<E> newNode = new Node<>(item, null);
        while (true) {
            Node<E> curTail = tail.get();
            Node<E> tailNext = curTail.next.get();
            if (curTail == tail.get()) {
                if (tailNext != null) {
                    // 队列处于中间状态，推进尾节点
                    tail.compareAndSet(curTail, tailNext);
                } else {
                    // 处于稳定状态，尝试插入新节点
                    if (curTail.next.compareAndSet(null, newNode)) {
                        // 插入成功，尝试推进尾节点
                        tail.compareAndSet(curTail, newNode);
                        return true;
                    }
                }
            }
        }
    }
}
