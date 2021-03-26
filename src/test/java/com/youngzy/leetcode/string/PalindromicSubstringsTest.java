package com.youngzy.leetcode.string;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PalindromicSubstringsTest {

    private PalindromicSubstrings ps;

    @Before
    public void setUp() throws Exception {
        ps = new PalindromicSubstrings3();
    }

    @Test
    public void test1() {
        assertEquals(3, ps.countSubstrings("abc"));
    }

    @Test
    public void test2() {
        assertEquals(6, ps.countSubstrings("aaa"));
    }
}