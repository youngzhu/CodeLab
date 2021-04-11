package com.youngzy.util;

import java.text.*;
import java.time.LocalDate;
import java.time.Period;
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

    public static Date parseDate(String date) {
        return parseDate(PATTERN_YMD, date);
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
     * 返回两个日期之间的月数差
     * 相当于 Excel中的 DATEDIFF(D1, D2, 'M') 函数
     *
     * 参考自Java 8 中的Period，主要用于8以下的版本
     *
     * @param d1
     * @param d2
     * @return
     */
    public static int getDiffMonth(Date d1, Date d2) {
        // 保证返回正数
        if (d2.before(d1)) {
            Date swap = d1;
            d1 = d2;
            d2 = swap;
        }

        int year1 = getYear(d1);
        int year2 = getYear(d2);
        int month1 = getMonth(d1);
        int month2 = getMonth(d2);
        int day1 = getDayOfMonth(d1);
        int day2 = getDayOfMonth(d2);

        int totalMonths1 = year1 * 12 + month1 - 1;
        int totalMonths2 = year2 * 12 + month2 - 1;
        int gapMonths = totalMonths2 - totalMonths1;
        int gapDays = day2 - day1;

        if (gapDays < 0)
            gapMonths--;

        // 注意，这里返回的n年m月，范围在 1-11
        return gapMonths % 12;
    }

    public static int getDiffMonthWithPeriod(Date d1, Date d2) {
        int y1 = getYear(d1);
        int m1 = getMonth(d1);
        int day1 = getDayOfMonth(d1);
        int y2 = getYear(d2);
        int m2 = getMonth(d2);
        int day2 = getDayOfMonth(d2);

        LocalDate localDate1 = LocalDate.of(y1, m1, day1);
        LocalDate localDate2 = LocalDate.of(y2, m2, day2);
        Period period = Period.between(localDate1, localDate2);

        return period.getMonths();
    }
}
