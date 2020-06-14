package com.youngzy.stackskills.datastructures.queue;

import java.lang.reflect.Array;

/**
 * 用数组实现一个队列
 */
public class CircularQueue<T> {
    private static final int SPECIAL_EMPTY_INDEX = -1;
    private static final int MAX_SIZE = 100;

    private T[] elements;
    private int head, tail;

    public CircularQueue(Class<T> clz) {
        head = SPECIAL_EMPTY_INDEX;
        tail = SPECIAL_EMPTY_INDEX;

        elements = (T[]) Array.newInstance(clz, MAX_SIZE);
    }

    public boolean isEmpty() {
        return head == SPECIAL_EMPTY_INDEX;
    }

    public boolean isFull() {
        // 头尾相连
        int next = (tail + 1) % MAX_SIZE;

        return next == head;
    }

    public void enqueue(T data) throws QueueOverflowException {
        if (isFull()) {
            throw new QueueOverflowException();
        }

        tail = (tail + 1) % MAX_SIZE;
        elements[tail] = data;

        if (head == SPECIAL_EMPTY_INDEX) {
            // 加入的第一个元素，将head也更新一下
            head = tail;
        }
    }

    public T dequeue() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        }

        T data = elements[head];

        if (head == tail) {
            // 只有一个元素，出列后又变成了空队列
            head = SPECIAL_EMPTY_INDEX;
        } else {
            head = (head + 1) % MAX_SIZE;
        }

        return data;
    }

    public static class QueueOverflowException extends Exception {}
    public static class QueueUnderflowException extends Exception {}
}


