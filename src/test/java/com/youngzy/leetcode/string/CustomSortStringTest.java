package com.youngzy.leetcode.string;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomSortStringTest {

    CustomSortString css;

    @Before
    public void setUp() throws Exception {
        css = new CustomSortString();
    }

    @Test
    public void test2() {
        assertEquals("cbad", css.customSortString("cba", "abcd"));
    }

    @Test
    public void test1() {
        assertEquals("kqeep", css.customSortString("kqep", "pekeq"));
    }
}