package com.youngzy.lpr;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class LPRTest {

    private static final BigDecimal loanAmount = new BigDecimal(600000); // 贷款本金
    private static final int loanTerm = 20; // 贷款年限
    private static final BigDecimal rateOfYear = new BigDecimal(0.05537D); // 年利率

    private LPR lpr = new LPR(loanAmount, loanTerm, rateOfYear);

    @Before
    public void init() {

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
}
