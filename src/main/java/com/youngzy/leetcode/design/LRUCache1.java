package com.youngzy.leetcode.design;

/*
 * 本想着参考Map类，学习和模仿的心态完全地自己写一个类。
 * 结果发现，Map类太复杂了，弃。。。
 *
 */
public class LRUCache1<K, V> implements LRUCache<K, V> {
    public LRUCache1(int capacity) {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        this.threshold = tableSizeFor(capacity);

        accessOrder = true;
    }

    public V get(K key) {
        Entry<K, V> e = null;
//        if ((e = getNode(hash(key), key)) == null)
//            return null;
//        if (accessOrder)
//            afterEntryAccess(e);
        return e.value;
    }

    public void put(int key, int value) {

    }

    static class Entry<K, V> {
        final int hash;
        final K key;
        V value;
        Entry<K, V> next;

        Entry<K, V> before, after;
        Entry(int hash, K key, V value, Entry<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    // eldest 最久未被使用的
    transient Entry<K, V> head;
    // youngest 最近被使用的
    transient Entry<K, V> tail;

    /**
     * 排序方式
     * true 按访问先后排序
     * false 按插入的顺序
     */
    final boolean accessOrder;

    // 来自 HashMap

    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * 最大容量
     * 一定是2的n次方
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    // 哈希表的加载因子
    final float loadFactor;

    // 再次扩充时的大小值
    // capacity * loadFactor
    int threshold;

    transient Entry<K,V>[] table;

    static final int tableSizeFor(int cap) {
        int n = -1 >>> Integer.numberOfLeadingZeros(cap - 1);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n;
    }

    /**
     * 计算 key 的哈希值，并将哈希值从高到低散列（通过“异或”的方式）。
     * 因为表使用2的n次方作为掩码，所以哈希值只有在当前掩码的位数之上才会重复。
     * （浮动秘钥是一个著名的例子，它在很小的表中存放了所有的连续数字。）
     * 所以我们向下扩散。这是一个在速度、效用和位扩散质量上的权衡。
     * 因为大部分的散列值都能合理地分布（所以不会从散列中获益），也因为我们
     * 用树来处理大的集合，使用异或来改变它的位数是减少系统丢失而采取的代价最小的方式，
     * 同时也整合了因为表的大小，在计算索引时根本不会被用到的高位值的影响。
     *
     * @param key
     * @return
     */
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    final Entry<K, V> getEntry(int hash, Object key) {
        Entry<K, V>[] tab;
        Entry<K, V> first, e;
        int n;
        K k;
        if ((tab = table) != null && (n = tab.length) > 0
                && (first = tab[(n -1) & hash]) != null
        ) {
            if (first.hash == hash // 永远判断 first 节点
                    && ((k = first.key) == key || (key != null && key.equals(k)))
            )
                return first;
            if ((e = first.next) != null) {
//                if (first instanceof TreeNode)
//                    return ((Entry<K, V>)first).getTreeNode(hash, key);
            }
        }
        return null;
    }
}
