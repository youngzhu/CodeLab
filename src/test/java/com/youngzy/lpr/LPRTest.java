package com.youngzy.lpr;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LPRTest {

    private static final BigDecimal LOAN_AMOUNT = new BigDecimal("600000"); // 贷款本金
    private static final int LOAN_TERM = 20; // 贷款年限
    private static final int PAID_TERM = 3; // 已还年限
    private static final BigDecimal BASE_RATE = new BigDecimal("4.8"); // 基准利率（百分比，4.9%）
    private static final BigDecimal EXERCISE_RATE = new BigDecimal("5.537"); // 执行（实际）利率

    private LPR lpr;

    @BeforeEach
    public void init() {
        lpr = new LPR(LOAN_AMOUNT, LOAN_TERM, PAID_TERM, EXERCISE_RATE, BASE_RATE);
    }


    @Test
    public void testCalculate1() {
        lpr.calculate1();
    }

    @Test
    public void calculate2() {
        lpr.calculate2();
    }

    @Test
    public void diff() {
        lpr.diff();
    }

    @Test
    public void rateTrending() {
        lpr.rateTrending();
    }

    @Test
    public void payPerMonth() {
        BigDecimal expected = new BigDecimal("4139.87");
        assertEquals(expected, lpr.payPerMonth());
    }

    @Test
    public void lprTrending() {
        lpr.lprTrending(LOAN_TERM);
    }

    @Test
    public void afterSwitchToLPR() {
        lpr.afterSwitchToLPR();
    }
}
