package com.youngzy.keepinmind.roman;

import org.junit.Test;

import static com.youngzy.keepinmind.roman.RomanNumeralParser.*;
import static com.youngzy.keepinmind.roman.RomanNumeral.*;
import static org.junit.Assert.*;

/**
 * @author youngzy
 * @since 2022-10-25
 */
public class RomanNumeralParserTest {

    @Test
    public void testParse_roman_success() {
        assertEquals(42, parse("XLII"));
    }

    @Test
    public void testParse_roman_fail() {
        String errorMsg = null;
        try {
            parse("FOO");
        } catch (Exception e) {
            errorMsg = e.getMessage();
        }
        assertEquals(INVALID_ROMAN_NUMERAL, errorMsg);
    }

    @Test
    public void testParse_arabic_success() {
        assertEquals("XLII", parse(42));
    }

    @Test
    public void testParse_arabic_fail() {
        String errorMsg = null;
        try {
            parse(4000);
        } catch (Exception e) {
            errorMsg = e.getMessage();
        }
        assertEquals(NUMERAL_MUST_BE_3999_OR_LESS, errorMsg);
    }
}
