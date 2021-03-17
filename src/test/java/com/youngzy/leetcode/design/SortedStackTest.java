package com.youngzy.leetcode.design;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SortedStackTest {
    private SortedStack sortedStack;

    @Before
    public void init() {
        sortedStack = new SortedStack2();
    }

    @Test
    public void test1() {
        sortedStack.push(1);
        sortedStack.push(2);
        sortedStack.peek();
        sortedStack.pop();
        assertEquals(2, sortedStack.peek());
    }

    @Test
    public void test2() {
        sortedStack.pop();
        sortedStack.pop();
        sortedStack.push(1);
        sortedStack.pop();
        assertTrue(sortedStack.isEmpty());
    }
}