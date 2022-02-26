package com.youngzy.book.codecomplete;

/**
 * 表驱动开发
 */
public class TableDriven {

    // 忽略闰年
    public int daysOfMonth(int month) {
        switch (month) {
            case 1:
                return 31;
            case 2:
                return 28;
            //...
            case 12:
                return 31;
            default:
                throw new RuntimeException("invalid month");
        }
    }

    // 这就是表驱动开发
    static final int[] DAYS_OF_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int daysOfMonthNew(int month) {
        return DAYS_OF_MONTH[month - 1];
    }
}
