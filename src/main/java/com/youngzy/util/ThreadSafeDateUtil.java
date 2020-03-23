package com.youngzy.util;

import java.text.*;
import java.util.Calendar;
import java.util.Date;

/**
 * 线程安全的日期工具类
 *
 * 在以前代码的基础上改进
 * Blog: http://www.youngzy.com/blog/2016/11/multi-thread-and-thread-safe/
 * GitHub: https://github.com/youngzhu/CollectionCode4Java/tree/master/src/org/young/thread
 */
public abstract class ThreadSafeDateUtil {

    public final static String PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public final static String PATTERN_YMD = "yyyy-MM-dd";

    private static ThreadLocal<DateFormat> formatter = new ThreadLocal<DateFormat>();

    private static DateFormat getDateFormat(String pattern) {
        DateFormat df = formatter.get();

        if (null == df) {
            df = new SimpleDateFormat(pattern);
            formatter.set(df);
        }

        return df;
    }

    private static DateFormat getDateFormat() {
        return getDateFormat(PATTERN_DEFAULT);
    }

    public static String formatDate(String pattern, Date date) {
        return getDateFormat(pattern).format(date);
    }

    public static Date parseDate(String pattern, String date) {
        try {
            return getDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();

        if (date == null) {
            calendar.setTime(new Date());
        } else {
            calendar.setTime(date);
        }

        return calendar;
    }

    public static int getYear(Date date) {
        Calendar calendar = getCalendar(date);

        return calendar.get(Calendar.YEAR);
    }

    public static int getMonth(Date date) {
        Calendar calendar = getCalendar(date);

        return calendar.get(Calendar.MONTH) + 1;
    }

    public static int getDayOfMonth(Date date) {
        Calendar calendar = getCalendar(date);

        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得2个日期之间的月数
     *
     * @param d1
     * @param d2
     * @return
     */
    public static int getDiffMonth(Date d1, Date d2) {
        // d2 较大
//        Assert.check(d2.compareTo(d1) >= 0);

        int result = 0;

        int year1 = getYear(d1);
        int year2 = getYear(d2);
        int month1 = getMonth(d1);
        int month2 = getMonth(d2);
        int day1 = getDayOfMonth(d1);
        int day2 = getDayOfMonth(d2);

        result = ((year2 - year1) * 12) + (month2 - month1);

        if (day2 >= day1) {
            result++;
        }

        return result;
    }
}
