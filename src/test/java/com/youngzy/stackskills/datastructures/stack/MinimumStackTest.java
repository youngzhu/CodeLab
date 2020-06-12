package com.youngzy.stackskills.datastructures.stack;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MinimumStackTest {
    MinimumStack minimumStack;

    @Before
    public void setUp() throws Exception {
        minimumStack = new MinimumStack();
    }

    @Test
    public void getMinimum() {
        int[] data = {3, 5, 3, 2, 0, 5};
        for (int i : data) {
            minimumStack.push(i);
        }

        assertEquals(2, minimumStack.getMinimum());
    }
}