package com.youngzy.leetcode.string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RegularExpressionMatchingTest {
    RegularExpressionMatching2 regExp;

    @Before
    public void init() {
        regExp = new RegularExpressionMatching2();
    }

    @Test
    public void test13() {
        Assert.assertTrue(regExp.isMatch("abbabaaaaaaacaa", "a*.*b.a.*c*b*a*c*"));
    }


    @Test
    public void test12() {
        Assert.assertFalse(regExp.isMatch("", "a"));
    }

    @Test
    public void test11() {
        Assert.assertFalse(regExp.isMatch("a", ""));
    }

    @Test
    public void test10() {
        Assert.assertFalse(regExp.isMatch("a", ".*..a*"));
    }

    @Test
    public void test9() {
        Assert.assertTrue(regExp.isMatch("a", "ab*"));
    }

    @Test
    public void test8() {
        Assert.assertFalse(regExp.isMatch("abcd", "d*"));
    }

    @Test
    public void test7() {
        Assert.assertTrue(regExp.isMatch("mississippi", "mis*is*ip*."));
    }

    @Test
    public void test6() {
        Assert.assertTrue(regExp.isMatch("aa", "a*"));
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
