package com.youngzy.book.algs4.ch01;

import java.util.Iterator;

/**
 * 能够动态调整大小的栈
 *
 * @author youngzy
 * @since 2021-08-07
 */
public class ResizingArrayStack<Item> implements Iterable<Item> {
    private Item[] a = (Item[]) new Object[1]; // 栈元素
    private int N; // 元素数量

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void resize(int max) {
        // 将栈元素移动到一个大小为max的新的数组
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++)
            temp[i] = a[i];
        a = temp;
    }

    public void push(Item item) {
        if (N == a.length)
            resize(2 * a.length);
        a[N++] = item;
    }

    public Item pop() {
        Item item = a[--N];
        a[N] = null; // 避免对象游离
        if (N > 0 && N == a.length / 4)
            resize(a.length / 2);
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    // 支持后进先出的迭代
    private class ReverseArrayIterator implements Iterator<Item> {

        private int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return a[--i];
        }
    }
}
