package com.youngzy.leetcode.string;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PalindromicSubstringsTest {

    private PalindromicSubstrings ps;

    @BeforeEach
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