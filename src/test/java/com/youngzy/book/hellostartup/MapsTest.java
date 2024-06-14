package com.youngzy.book.hellostartup;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class MapsTest {

    @Test
    void sortedMapAlreadySorted() {
        Map<String, Integer> map = new TreeMap<>();

        map.put("Andy", 10);
        map.put("Zark", 6);
        map.put("Michael", 10);

        // Map，只校验了值，不校验顺序
//        Map<String, Integer> excepted = ImmutableMap.of("Andy", 10, "Zark", 6, "Michael", 10);
//        Map<String, Integer> excepted = ImmutableMap.of("Andy", 10, "Zark", 6, "Michael", 1);
        Map<String, Integer> excepted = ImmutableMap.of("Andy", 10, "Michael", 10, "Zark", 6);
        Map<String, Integer> actual = Maps.sortedMap(map);

        assertEquals(excepted, actual);
        // class 也要一致
//        assertSame(excepted, actual);
    }

    @Test
    void sortedMapNonSorted() {
        Map<String, Integer> map = new HashMap<>();

        map.put("Zark", 6);
        map.put("Michael", 10);
        map.put("Andy", 10);

        // Map，只校验了值，不校验顺序
//        Map<String, Integer> excepted = ImmutableMap.of("Andy", 10, "Zark", 6, "Michael", 10);
        Map<String, Integer> excepted = ImmutableMap.of("Andy", 10, "Zark", 6, "Michael", 1);
//        Map<String, Integer> excepted = ImmutableMap.of("Andy", 10, "Michael", 10, "Zark", 6);

        Comparator compareByValue = (key1, key2) -> map.get(key2).compareTo(map.get(key1));
        Map<String, Integer> actual = Maps.sortedMap(map, compareByValue);

        assertEquals(excepted, actual);
    }
}