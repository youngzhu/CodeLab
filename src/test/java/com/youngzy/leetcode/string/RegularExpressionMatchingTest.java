package com.youngzy.leetcode.string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RegularExpressionMatchingTest {
    RegularExpressionMatching regExp;

    @Before
    public void init() {
        regExp = new RegularExpressionMatching();
    }

    @Test
    public void test5() {
        Assert.assertTrue(regExp.isMatch("ab", ".*.."));
    }

    @Test
    public void test4() {
        Assert.assertTrue(regExp.isMatch("bbbba", ".*a*a"));
    }

    @Test
    public void test3() {
        Assert.assertFalse(regExp.isMatch("a", "ab*a"));
    }

    @Test
    public void test2() {
        Assert.assertFalse(regExp.isMatch("aa", "."));
    }

    @Test
    public void test1() {
        Assert.assertFalse(regExp.isMatch("ab", ".*c"));
    }
}
