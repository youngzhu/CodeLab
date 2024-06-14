package com.youngzy.book.hellostartup;

import com.google.common.collect.ImmutableList;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author youngzy
 * @since 2024-06-14
 */
public class WordParser {
    private final Set<String> stopWords;

    private static final String PUNCTUATION_AND_WHITESPACE = "[\\p{Punct}\\s]+";

    public WordParser(Set<String> stopWords) {
        this.stopWords = stopWords;
    }

    public List<String> splitTextIntoNormalizedWords(String text) {
//        return ImmutableList.copyOf(text.split(PUNCTUATION_AND_WHITESPACE));

        return Arrays.stream(text.split(PUNCTUATION_AND_WHITESPACE))
                .map(String::toLowerCase)
                .filter(word -> !word.isEmpty() && !stopWords.contains(word))
                .collect(Collectors.toList());
    }
}
