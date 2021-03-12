package com.youngzy.leetcode.design;

import java.util.*;

/**
 * 官方解，跟1差不多，只是 Array 换成了 List
 *
 * List 还没 数组好用啊。。。。
 */
public class RandomizedSet2 implements RandomizedSet {
    private final List<Integer> list; // 数据集合
    private final Map<Integer, Integer> valVSIdx; // 值-索引
    private final Random random;

    public RandomizedSet2() {
        list = new ArrayList<>();
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
        int lastIdx = list.size() - 1;
        list.set(idx, list.get(lastIdx));
        valVSIdx.put(list.get(lastIdx), idx);
        list.remove(lastIdx);
        valVSIdx.remove(val);

        return true;
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}
