package com.youngzy.leetcode.design;

import org.junit.Test;

import static org.junit.Assert.*;

public class RandomizedSetTest {
    @Test
    public void test() {
        // 初始化一个空的集合。
//        RandomizedSet randomSet = new RandomizedSet1();
//        RandomizedSet randomSet = new RandomizedSet2();
        RandomizedSet randomSet = new RandomizedSet3();

        // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
        assertTrue(randomSet.insert(1));

        // 返回 false ，表示集合中不存在 2 。
        assertFalse(randomSet.remove(2));

        // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
        assertTrue(randomSet.insert(2));

        // getRandom 应随机返回 1 或 2 。
        int random = randomSet.getRandom();
        System.out.println(random);

        // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
        assertTrue(randomSet.remove(1));

        // 2 已在集合中，所以返回 false 。
        assertFalse(randomSet.insert(2));

        // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
        assertEquals(2, randomSet.getRandom());
    }
}