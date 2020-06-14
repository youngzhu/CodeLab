package com.youngzy.stackskills.datastructures.heap;

import java.lang.reflect.Array;
import java.util.Arrays;

public abstract class Heap<T extends Comparable> {
    private static int MAX_SIZE = 0;

    private T[] array;
    private int count = 0;

    public Heap(Class<T> clazz) {
        this(clazz, MAX_SIZE);
    }

    public Heap(Class<T> clazz, int size) {
        array = (T[]) Array.newInstance(clazz, size);

    }

    public int getLeftChildIndex(int index) {
        int result = 2 * index + 1;

        if (result >= count) {
            return -1;
        }

        return result;
    }

    public int getRightChildIndex(int index) {
        int result = 2 * index + 2;
        if (result >= count) {
            return -1;
        }

        return result;
    }

    public int getParentIndex(int index) {
        if (index < 0 || index > count) {
            return -1;
        }

        return (index - 1) / 2;
    }

    protected void swap(int idx1, int idx2) {
        T tmp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = tmp;
    }

    /**
     * 将新元素放在末尾，然后慢慢上移，找到合适的位置
     * @param value
     */
    public void insert(T value) {
        if (isFull()) {
            // 也可以抛出异常
            return;
        }

        array[count] = value;
        siftUp(count);

        count++;
    }

    /**
     * 1. 移去第一个元素
     * 2. 将最后一个元素移到第一个
     * 3. 将第一个元素慢慢下移，找到合适的位置
     *
     * @return
     */
    public T removeHighestPriority() {
        T highest = getHighestPriority();

        array[0] = array[count - 1];
        count --;
        siftDown(0);

        return highest;
    }

    protected T getHighestPriority() {
        if (isEmpty()) {
            return null;
        }

        return array[0];
    }

    protected abstract void siftDown(int idx);

    protected abstract void siftUp(int idx);

    public int getCount() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == array.length;
    }

    public T getElementAtIndex(int idx) {
        return array[idx];
    }
}
