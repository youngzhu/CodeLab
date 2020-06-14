package com.youngzy.stackskills.datastructures.list;

import java.util.LinkedList;

/**
 * LinkedList 和 Array 的比较
 */
public class LinkedListVSArray {
    LinkedList<Integer> list;
    int[] array;

    /**
     * 装载数据
     */
    public void loadDataWithLinkedList(int size) {
        list = new LinkedList();

        for (int i = 0; i < size; i ++) {
            list.add(i);
        }

    }

    /**
     *
     * 装载数据
     */
    public void loadDataWithArray(int size) {
        array = new int[size];

        for (int i = 0; i < size; i ++) {
            array[i] = i;
        }

    }

    public int randomAccessWithLinkedList(int index) {
        return list.get(index);
    }

    public int randomAccessWithLinkedList2(int index) {
        Integer[] arr = list.toArray(new Integer[0]);
        return arr[index];
    }

    public int randomAccessWithArray(int index) {
        return array[index];
    }

}
