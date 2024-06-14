package com.youngzy.leetcode.string;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PermutationTest {

    Permutation foo;

    @Test
    public void permutation() {
        String[] expected = {"qwe","qew","wqe","weq","ewq","eqw"};

        assertArrayEquals(expected, foo.permutation("qwe"));
    }

    @BeforeEach
    public void setUp() throws Exception {
        foo = new Permutation();
    }
}