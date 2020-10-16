package com.youngzy.leetcode.design;

import org.junit.Test;

import static org.junit.Assert.*;

public class WordDictionaryTest {

    @Test
    public void test() {
        WordDictionary wordDictionary = new WordDictionary0();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        assertFalse(wordDictionary.search("pad")); // return False
        assertTrue(wordDictionary.search("bad")); // return True
        assertTrue(wordDictionary.search(".ad")); // return True
        assertTrue(wordDictionary.search("b..")); // return True
        assertTrue(wordDictionary.search("m.d")); // return True
    }

    /*
"","search","search","search","search","search","search"]
[""],[""],[""],[""],["b."],["a.d"],["."]]

true,false]
     */
    @Test
    public void test1() {
        WordDictionary wordDictionary = new WordDictionary0();
        wordDictionary.addWord("at");
        wordDictionary.addWord("and");
        wordDictionary.addWord("an");
        wordDictionary.addWord("add");
//        assertFalse(wordDictionary.search("a")); // return False
//        assertFalse(wordDictionary.search(".at")); // return False
        wordDictionary.addWord("bat");
//        assertTrue(wordDictionary.search(".at")); // return True
//        assertTrue(wordDictionary.search("an.")); // return True
//        assertFalse(wordDictionary.search("a.d.")); // return False
//        assertFalse(wordDictionary.search("a.d.")); // return False
        assertTrue(wordDictionary.search("a.d")); // return True
//        assertFalse(wordDictionary.search(".")); // return True
    }
}