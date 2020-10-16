package com.youngzy.leetcode.trie;

import org.junit.Test;

import static org.junit.Assert.*;

public class TrieTest {

    @Test
    public void test() {
        Trie trie = new Trie();
        trie.insert("she");
        assertTrue(trie.search("she"));
        assertFalse(trie.search("shell"));

        trie.insert("shell");
        assertTrue(trie.search("she"));
        assertTrue(trie.search("shell"));
    }
}