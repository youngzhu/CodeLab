package com.youngzy.book.hellostartup;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Map的顺序是个玄学。。
 * 它的长处也不在于有序
 * 不纠结这个了
 *
 * @author youngzy
 * @since 2024-06-14
 */
public class Maps {
    private Maps() {
    }

    public static <K, V> SortedMap<K, V> sortedMap(Map<K, V> map) {
        return sortedMap(map, null);
    }

    public static <K, V> SortedMap<K, V> sortedMap(Map<K, V> map, Comparator comparator) {
        if (map == null) {
            return null;
        }

        if (map instanceof SortedMap) {
            return (SortedMap<K, V>) map;
        }

        TreeMap sortedMap = new TreeMap();
        // 加上 comparator 会丢掉相同value的数据，Map中的数据都少了
//        TreeMap sortedMap = new TreeMap(comparator);
        sortedMap.putAll(map);
        return sortedMap;
    }
}
