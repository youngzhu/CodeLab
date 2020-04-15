package com.youngzy.stackskills.ral;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HelloRecursionTest {

    HelloRecursion foo;

    @Before
    public void setUp() throws Exception {
        foo = new HelloRecursion();
    }

    @Test
    public void reverseEmptyString() {
        assertEquals("", foo.reverseString(""));
    }

    @Test
    public void reverseOneCharacter() {
        assertEquals("A", foo.reverseString("A"));
    }

    @Test
    public void reverseString() {
        assertEquals("321CBA", foo.reverseString("ABC123"));
    }

    @Test
    public void reverseOddNumberLength() {
        assertEquals("zyx", foo.reverseString("xyz"));
    }
}