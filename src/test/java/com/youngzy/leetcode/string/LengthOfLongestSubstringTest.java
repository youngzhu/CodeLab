package com.youngzy.leetcode.string;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LengthOfLongestSubstringTest {

    @Test
    public void test() {
        LengthOfLongestSubstring lols = new LengthOfLongestSubstring();

//        Assert.assertEquals(3, lols.lengthOfLongestSubstring("abaca"));
//        Assert.assertEquals(4, lols.lengthOfLongestSubstring("aabcad"));
        assertEquals(3, lols.lengthOfLongestSubstring("pwwkew"));
    }

}
