package com.youngzy.leetcode.string;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegularExpressionMatchingTest {
    RegularExpressionMatching4 regExp;

    @BeforeEach
    public void init() {
        regExp = new RegularExpressionMatching4();
    }

    @Test
    public void test16() {
        assertTrue(regExp.isMatch("", ".*"));
    }

    @Test
    public void test15() {
        assertFalse(regExp.isMatch("ab", ".c"));
    }

    @Test
    public void test14() {
        assertTrue(regExp.isMatch("aaa", "a.a"));
    }

    @Test
    public void test13() {
        assertTrue(regExp.isMatch("abbabaaaaaaacaa", "a*.*b.a.*c*b*a*c*"));
    }

    @Test
    public void test12() {
        assertFalse(regExp.isMatch("", "a"));
    }

    @Test
    public void test11() {
        assertFalse(regExp.isMatch("a", ""));
    }

    @Test
    public void test10() {
        assertFalse(regExp.isMatch("a", ".*..a*"));
    }

    @Test
    public void test9() {
        assertTrue(regExp.isMatch("a", "ab*"));
    }

    @Test
    public void test8() {
        assertFalse(regExp.isMatch("abcd", "d*"));
    }

    @Test
    public void test7() {
        assertTrue(regExp.isMatch("mississippi", "mis*is*ip*."));
    }

    @Test
    public void test6() {
        assertTrue(regExp.isMatch("aa", "a*"));
    }

    @Test
    public void test5() {
        assertTrue(regExp.isMatch("ab", ".*.."));
    }

    @Test
    public void test4() {
        assertTrue(regExp.isMatch("bbbba", ".*a*a"));
    }

    @Test
    public void test3() {
        assertFalse(regExp.isMatch("a", "ab*a"));
    }

    @Test
    public void test2() {
        assertFalse(regExp.isMatch("aa", "."));
    }

    @Test
    public void test1() {
        assertFalse(regExp.isMatch("ab", ".*c"));
    }
}
