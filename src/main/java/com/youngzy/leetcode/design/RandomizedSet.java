package com.youngzy.leetcode.design;

/**
 * 380. 常数时间插入、删除和获取随机元素
 *
 * 设计一个支持在平均时间复杂度 O(1)下，执行以下操作的数据结构。
 *
 * insert(val)：当元素 val 不存在时，向集合中插入该项。
 * remove(val)：元素 val 存在时，从集合中移除该项。
 * getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。
 *
 * 示例 :
 *
 * // 初始化一个空的集合。
 * RandomizedSet randomSet = new RandomizedSet();
 *
 * // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
 * randomSet.insert(1);
 *
 * // 返回 false ，表示集合中不存在 2 。
 * randomSet.remove(2);
 *
 * // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
 * randomSet.insert(2);
 *
 * // getRandom 应随机返回 1 或 2 。
 * randomSet.getRandom();
 *
 * // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
 * randomSet.remove(1);
 *
 * // 2 已在集合中，所以返回 false 。
 * randomSet.insert(2);
 *
 * // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
 * randomSet.getRandom();
 *
 * 链接：https://leetcode-cn.com/problems/insert-delete-getrandom-o1
 */

/*
这道题的精髓在于：
常数时间插入-直接加在末尾
常数时间删除-需要有值-索引的对应关系（决定了数据结构：Map+数组或列表）
            用交换来避免移动：将末尾值和删除值交换位置，然后删除末尾元素
常数时间获取-get(randomIdx)

另一个认知是：并不是结构越复杂越高级越好的。高级的东西也有其适用的场景，不是万能的。
 */
interface RandomizedSet {

    /** Inserts a value to the set.
     * Returns true if the set did not already contain the specified element. */
    boolean insert(int val);

    /** Removes a value from the set.
     * Returns true if the set contained the specified element. */
    boolean remove(int val) ;

    /** Get a random element from the set. */
    public int getRandom() ;
}
