package com.youngzy.stackskills.ral;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HelloRecursionTest {

    HelloRecursion foo;

    @BeforeEach
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