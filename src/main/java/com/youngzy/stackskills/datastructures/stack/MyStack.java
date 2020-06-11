package com.youngzy.stackskills.datastructures.stack;

/**
 * 用链表实现一个栈
 *
 * @param <T>
 */
public class MyStack<T> {
    private static int MAX_SIZE = 100;

    private Element<T> top;
    private int size;

    public void push(T data) throws StackOverflowException {
        if (size == MAX_SIZE) {
            throw new StackOverflowException();
        }

        Element e = new Element(data, top);
        top = e;

        size++;
    }

    public T pop() throws StackUnderflowException {
        if (size == 0) {
            throw new StackUnderflowException();
        }

        T data = top.data;
        top = top.next;

        size--;

        return data;
    }

    public T peek() throws StackUnderflowException {
        if (size == 0) {
            throw new StackUnderflowException();
        }

        return top.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == MAX_SIZE;
    }

    public int size() {
        return size;
    }
}
