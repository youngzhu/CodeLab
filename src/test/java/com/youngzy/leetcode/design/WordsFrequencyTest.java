package com.youngzy.leetcode.design;

import org.junit.Test;

import static org.junit.Assert.*;

public class WordsFrequencyTest {

    @Test
    public void test() {
        WordsFrequency wordsFrequency = new WordsFrequency(new String[]{"i", "have", "an", "apple", "he", "have", "a", "pen"});
        assertEquals(0, wordsFrequency.get("you")); //返回0，"you"没有出现过
        assertEquals(2, wordsFrequency.get("have")); //返回2，"have"出现2次
        assertEquals(1, wordsFrequency.get("an")); //返回1
        assertEquals(1, wordsFrequency.get("apple")); //返回1
        assertEquals(1, wordsFrequency.get("pen")); //返回1
    }
}