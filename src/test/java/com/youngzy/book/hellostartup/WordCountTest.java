package com.youngzy.book.hellostartup;

import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multiset;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class WordCountTest {
    final Set<String> stopWords = ImmutableSet.of("and", "the", "to");
    final WordParser wordParser = new WordParser(stopWords);
    final WordCount wordCount = new WordCount(wordParser);

    @Test
    void calculateWordCountOnFourWords() {
        String text = "Hello! Hello startup people! Hello startup world!";

        Multiset<String> expected = ImmutableMultiset.<String>builder()
                .addCopies("hello", 3)
                .addCopies("startup", 2)
                .addCopies("people", 1)
                .addCopies("world", 1)
                .build();

        Multiset<String> actual = wordCount.calculateWordCount(text);

        assertEquals(expected, actual);
    }

    @Test
    void calculateWordCountIgnoresStopWords() {
        String text = "Hello! Hello to the startup people! And hello to the startup world!";

        Multiset<String> expected = ImmutableMultiset.<String>builder()
                .addCopies("hello", 3)
                .addCopies("startup", 2)
                .addCopies("people", 1)
                .addCopies("world", 1)
                .build();

        Multiset<String> actual = wordCount.calculateWordCount(text);

        assertEquals(expected, actual);
    }
}