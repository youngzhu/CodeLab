package com.youngzy.leetcode.design;

import java.util.*;

/**
 * 试试LinkedList，因为有removeLast
 *
 * 效率更差了。。大概是要维护next指针
 * 数组居然是最高效的。
 */
public class RandomizedSet3 implements RandomizedSet {
    private final LinkedList<Integer> list; // 数据集合
    private final Map<Integer, Integer> valVSIdx; // 值-索引
    private final Random random;

    public RandomizedSet3() {
        list = new LinkedList<>();
        valVSIdx = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (valVSIdx.containsKey(val)) {
            return false;
        }

        list.add(val);
        valVSIdx.put(val, list.size() - 1);

        return true;
    }

    public boolean remove(int val) {
        if (! valVSIdx.containsKey(val)) {
            return false;
        }

        // 交换并移除
        int idx = valVSIdx.get(val);
        if (idx != list.size() - 1) {
            int lastVal = list.getLast();
            list.set(idx, lastVal);
            valVSIdx.put(lastVal, idx);
        }
        list.removeLast();
        valVSIdx.remove(val);

        return true;
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}
