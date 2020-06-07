package com.youngzy.stackskills.dsa.stack;

public class Element<T> {
    T data;
    Element next;

    public Element(T data, Element next) {
        this.data = data;
        this.next = next;
    }
}
