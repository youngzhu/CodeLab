package com.youngzy.book.hellostartup;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class WordParserTest {
    final Set<String> stopWords = ImmutableSet.of("and", "the", "to");
    final WordParser wordParser = new WordParser(stopWords);

    @Test
    void splitTextIntoNormalizedWordsWithPunctuation() {
        String text = "Hello! Can you hear me? This should, um, ignore punctuation.";

        List<String> expected = ImmutableList.of("hello", "can", "you", "hear",
                "me", "this", "should", "um", "ignore", "punctuation");

        List<String> actual = wordParser.splitTextIntoNormalizedWords(text);

        assertEquals(expected, actual);
    }

    @Test
    void splitTextIntoNormalizedWordsEmptyString() {
        List<String> out = wordParser.splitTextIntoNormalizedWords("");

        assertTrue(out.isEmpty());
    }

    @Test
    void splitTextIntoNormalizedWordsDifferentWhitespace() {
        String text = "Hello\nthere!\t\tIs this    working? ";

        List<String> expected = ImmutableList.of("hello", "there", "is", "this", "working");

        List<String> actual = wordParser.splitTextIntoNormalizedWords(text);

        assertEquals(expected, actual);
    }

    @Test
    void splitTextIntoNormalizedWordsRemovesStopWords() {
        String text = "Hello to you and all the best!";

        List<String> expected = ImmutableList.of("hello", "you", "all", "best");

        List<String> actual = wordParser.splitTextIntoNormalizedWords(text);

        assertEquals(expected, actual);
    }
}