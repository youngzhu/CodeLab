package com.youngzy.leetcode.design;

import com.youngzy.util.RegularExpressionUtil;
import org.junit.Test;

import static org.junit.Assert.*;

public class LRUCacheTest {

    LRUCache3 cache;

    private void createCache(int capacity) {
        cache = new LRUCache3(capacity);
    }

    @Test
    public void test() {
        createCache(2);
        cache.put(1, 1); // 缓存是 {1=1}
        cache.put(2, 2); // 缓存是 {1=1, 2=2}
        assertEquals(1, cache.get(1));    // 返回 1
        cache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        assertEquals(-1, cache.get(2));    // 返回 -1 (未找到)
        cache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        assertEquals(-1, cache.get(1));    // 返回 -1 (未找到)
        assertEquals(3, cache.get(3));    // 返回 3
        assertEquals(4, cache.get(4));    // 返回 4
    }

    @Test
    public void test2() {
        createCache(2);
        assertEquals(-1, cache.get(2));
        cache.put(2, 6);
        assertEquals(-1, cache.get(1));
        cache.put(1, 5);
        cache.put(1, 2);
        assertEquals(2, cache.get(1));
        assertEquals(6, cache.get(2));
    }

    @Test
    public void test3() {
        createCache(2);
        assertEquals(-1, cache.get(2));
        cache.put(2, 6);
        assertEquals(-1, cache.get(1));
        cache.put(1, 5);
        cache.put(2, 2);
        cache.put(3, 2);
        cache.put(4, 2);
        cache.put(3, 2);
        cache.put(5, 2);
        assertEquals(-1, cache.get(1));
        assertEquals(-1, cache.get(2));
    }

    @Test
    public void test4() {
        String input = "[10],[10,13],[3,17],[6,11],[10,5],[9,10],[13],[2,19],[2],[3],[5,25],[8],[9,22],[5,5],[1,30],[11],[9,12],[7],[5],[8],[9],[4,30],[9,3],[9],[10],[10],[6,14],[3,1],[3],[10,11],[8],[2,14],[1],[5],[4],[11,4],[12,24],[5,18],[13],[7,23],[8],[12],[3,27],[2,12],[5],[2,9],[13,4],[8,18],[1,7],[6],[9,29],[8,21],[5],[6,30],[1,12],[10],[4,15],[7,22],[11,26],[8,17],[9,29],[5],[3,4],[11,30],[12],[4,29],[3],[9],[6],[3,4],[1],[10],[3,29],[10,28],[1,20],[11,13],[3],[3,12],[3,8],[10,9],[3,26],[8],[7],[5],[13,17],[2,27],[11,15],[12],[9,19],[2,15],[3,16],[1],[12,17],[9,1],[6,19],[4],[5],[5],[8,1],[11,7],[5,2],[9,28],[1],[2,2],[7,4],[4,22],[7,24],[9,26],[13,28],[11,26]";
        String expected = "null,null,null,null,null,null,-1,null,19,17,null,-1,null,null,null,-1,null,-1,5,-1,12,null,null,3,5,5,null,null,1,null,-1,null,30,5,30,null,null,null,-1,null,-1,24,null,null,18,null,null,null,null,-1,null,null,18,null,null,-1,null,null,null,null,null,18,null,null,-1,null,4,29,30,null,12,-1,null,null,null,null,29,null,null,null,null,17,22,18,null,null,null,-1,null,null,null,20,null,null,null,-1,18,18,null,null,null,null,20,null,null,null,null,null,null,null";

        String[] values = RegularExpressionUtil.getValuesBetweenBrackets(input);
        String[] expecteds = expected.split(",");

        int capacity = Integer.valueOf(values[0]);
        createCache(capacity);

        for (int i = 1; i < values.length; i++) {
//            System.out.println(i + ":" + cache.toString());
//            System.out.println(i + "-map:" + cache.map.keySet());

            boolean isNum = true;
            Integer number = null;
            try {
                number = Integer.valueOf(values[i]);
            } catch (NumberFormatException e) {
                isNum = false;
            }
            if (isNum) {
//                System.out.print("  get-");
                assertEquals(Integer.valueOf(expecteds[i]).intValue(), cache.get(number));
            } else {
//                System.out.print("  put-");
                String[] puts = values[i].split(",");
                cache.put(Integer.valueOf(puts[0]), Integer.valueOf(puts[1]));
            }
//            System.out.println(values[i]);
        }
    }

}