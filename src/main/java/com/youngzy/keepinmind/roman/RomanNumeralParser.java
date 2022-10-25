package com.youngzy.keepinmind.roman;

/**
 * @author youngzy
 * @since 2022-10-25
 */
public class RomanNumeralParser {
    static final String INVALID_ROMAN_NUMERAL =
            "Invalid Roman numeral";

    public static int parse(String s) {
        if (! s.matches("[IVXLXCDM]+"))
            throw new NumberFormatException(INVALID_ROMAN_NUMERAL);

        return new RomanNumeral(s).toArabic();
    }

    public static String parse(int i) {
        return new RomanNumeral(i).toRoman();
    }
}
