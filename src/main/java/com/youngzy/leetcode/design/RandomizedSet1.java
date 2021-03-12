package com.youngzy.leetcode.design;

import java.util.HashMap;
import java.util.Map;

/**
 * 别人的想法：
 * 数组存储值（实现O(1)插入、随机读），
 * 哈希表存储值对应的索引（实现O(1)查找）
 * 删除时将要删除的数与数组最后一个数交换位置（避免移动。实现O(1)删除）
 *
 * 自己也想过数组和Map，但没想明白该怎么用
 */
public class RandomizedSet1 implements RandomizedSet {
    int[] set; // 存数据的数组
    int currentIdx = 0; // 当前数组下标
    Map<Integer, Integer> valVSIdx; // 数值和下标的对应关系

    /** Initialize your data structure here. */
    public RandomizedSet1() {
        set = new int[1000];
        valVSIdx = new HashMap<>();
    }

    /** Inserts a value to the set.
     * Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (valVSIdx.containsKey(val))
            return false;

        set[currentIdx] = val;
        valVSIdx.put(val, currentIdx);
        currentIdx++;

        return true;
    }

    /** Removes a value from the set.
     * Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (! valVSIdx.containsKey(val))
            return false;

        // 删除时将要删除的数与数组最后一个数交换位置（避免移动。实现O(1)删除）
        int idx = valVSIdx.get(val);
        int lastVal = set[currentIdx - 1];
        set[idx] = lastVal;
        valVSIdx.put(lastVal, idx);
        valVSIdx.remove(val);
        currentIdx--;

        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int randomIdx = (int) (Math.random() * currentIdx);

        return set[randomIdx];
    }
}
