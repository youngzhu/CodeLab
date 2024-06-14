package com.youngzy.book.hellostartup;

import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;

import java.util.List;

/**
 * @author youngzy
 * @since 2024-06-14
 */
public class WordCount {
    private final WordParser wordParser;

    public WordCount(WordParser wordParser) {
        this.wordParser = wordParser;
    }

    public Multiset<String> calculateWordCount(String text) {
        List<String> words = wordParser.splitTextIntoNormalizedWords(text);
        ImmutableMultiset<String> count = ImmutableMultiset.copyOf(words);
        return Multisets.copyHighestCountFirst(count);
    }
}
