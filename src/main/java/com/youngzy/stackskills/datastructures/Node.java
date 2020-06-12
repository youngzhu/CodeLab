package com.youngzy.stackskills.datastructures;

public class Node<T extends  Comparable<T>> {
    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        next = null;
    }
}
