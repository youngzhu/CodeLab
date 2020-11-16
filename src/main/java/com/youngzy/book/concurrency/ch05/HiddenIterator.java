package com.youngzy.book.concurrency.ch05;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 5-6 隐藏在字符串拼接中的迭代（不要这么做）
 */
public class HiddenIterator {
    private final Set<Integer> set = new HashSet<>();

    public synchronized void add(Integer integer) {
        set.add(integer);
    }

    public synchronized void remove(Integer integer) { set.remove(integer); }

    public void addTenThings() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            add(r.nextInt());
        }

        System.out.println("DEBUG: added ten elements to " + set);
    }


}
