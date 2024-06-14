package com.youngzy.leetcode.string;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomSortStringTest {

    CustomSortString css;

    @BeforeEach
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