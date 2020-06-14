package com.youngzy.stackskills.datastructures.list;

import com.youngzy.stackskills.datastructures.list.Node;

public class MyLinkedList<T extends Comparable<T>> implements Cloneable {
    private Node<T> head;

    public int countNodes() {
        if (head == null) {
            return 0;
        }

        Node<T> curr = head;
        int count = 0;
        while (curr != null) {
            curr = curr.next;
            count ++;
        }

        return count;
    }

    public void addNode(T data) {
        if (head == null) {
            head = new Node<>(data);
        }

        Node<T> curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }

        curr.next = new Node<>(data);
    }

    public T popElement() {
        if (head == null) {
            return null;
        }

        T top = head.data;

        head = head.next;

        return top;
    }
}
