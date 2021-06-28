package com.youngzy.util;

import org.junit.Test;

import static org.junit.Assert.*;
import static com.youngzy.util.RegularExpressionUtil.*;

public class RegularExpressionUtilTest {
    @Test
    public void testGetValuesBetweenBrackets() {
        String exp = "[10],[10,13]";

        String[] expecteds = new String[]{"10", "10,13"};
        String[] actuals = getValuesBetweenBrackets(exp);

        assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void testGetValuesBetweenBrackets0() {
        String exp = "[10]";

        String[] expecteds = new String[]{"10"};
        String[] actuals = getValuesBetweenBrackets(exp);

        assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void testGetValuesBetweenBrackets1() {
        String exp = "[10],[2]";

        String[] expecteds = new String[]{"10", "2"};
        String[] actuals = getValuesBetweenBrackets(exp);

        assertArrayEquals(expecteds, actuals);
    }
}