package com.youngzy.util;

import org.junit.Test;

import static org.junit.Assert.*;
import static com.youngzy.util.ThreadSafeDateUtil.*;

public class ThreadSafeDateUtilTest {

    @Test
    public void testGetDiffMonth() {
        assertEquals(8, getDiffMonth(parseDate("2021-08-13"), parseDate("2022-05-01")));
        assertEquals(9, getDiffMonth(parseDate("2021-08-13"), parseDate("2022-05-15")));
        assertEquals(9, getDiffMonth(parseDate("2021-08-15"), parseDate("2022-05-15")));
        assertEquals(8, getDiffMonth(parseDate("2021-08-16"), parseDate("2022-05-15")));

        // end < begin
        assertEquals(8, getDiffMonth(parseDate("2022-05-15"), parseDate("2021-08-16")));
    }

    @Test
    public void testGetDiffMonthWithPeriod() {
        assertEquals(8, getDiffMonthWithPeriod(parseDate("2021-08-13"), parseDate("2022-05-01")));
        assertEquals(9, getDiffMonthWithPeriod(parseDate("2021-08-13"), parseDate("2022-05-15")));
        assertEquals(9, getDiffMonthWithPeriod(parseDate("2021-08-15"), parseDate("2022-05-15")));
        assertEquals(8, getDiffMonthWithPeriod(parseDate("2021-08-16"), parseDate("2022-05-15")));
    }
}