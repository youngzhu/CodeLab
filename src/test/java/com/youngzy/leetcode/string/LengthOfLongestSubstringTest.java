package com.youngzy.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

public class LengthOfLongestSubstringTest {

    @Test
    public void test() {
        LengthOfLongestSubstring lols = new LengthOfLongestSubstring();

//        Assert.assertEquals(3, lols.lengthOfLongestSubstring("abaca"));
//        Assert.assertEquals(4, lols.lengthOfLongestSubstring("aabcad"));
        Assert.assertEquals(3, lols.lengthOfLongestSubstring("pwwkew"));
    }

}
