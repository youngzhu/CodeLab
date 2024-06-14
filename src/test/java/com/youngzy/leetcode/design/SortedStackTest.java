package com.youngzy.leetcode.design;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SortedStackTest {
    private SortedStack sortedStack;

    @BeforeEach
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