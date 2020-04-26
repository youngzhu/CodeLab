package com.youngzy.leetcode.string;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PermutationTest {

    Permutation foo;

    @Test
    public void permutation() {
        String[] expected = {"qwe","qew","wqe","weq","ewq","eqw"};

        assertArrayEquals(expected, foo.permutation("qwe"));
    }

    @Before
    public void setUp() throws Exception {
        foo = new Permutation();
    }
}