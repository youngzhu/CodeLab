package com.youngzy.lpr;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 *
 * 等额本金：
 * 每月还款金额 = （贷款本金 / 还款月数）+（本金 — 已归还本金累计额）×每月利率
 */
public class LPR {
    private static final BigDecimal MONTHS_OF_YEAR = new BigDecimal(12);
    // 注意这里的数字不是小数点有效位
    // 而是从第一个非0算起的有效数字个数
    private static final MathContext MC_4_RATE = new MathContext(10);
    // 6=每月还款 4 位数 + 2个小数位
    private static final MathContext MC_4_MONEY = new MathContext(6);

    private BigDecimal loanAmount; // 贷款本金
    private int loanTerm; // 贷款年限
    private BigDecimal rateOfYear; // 年利率

    public LPR(BigDecimal loanAmount, int loanTerm, BigDecimal rateOfYear) {
        this.loanAmount = loanAmount;
        this.loanTerm = loanTerm;
        this.rateOfYear = rateOfYear;
    }

    /**
     * 等额本息：
     *   〔贷款本金×月利率×（1＋月利率）＾还款月数〕÷〔（1＋月利率）＾还款月数－1〕
     */
    public void calculate1() {
        BigDecimal sum = BigDecimal.ZERO;

        // 月利率
        BigDecimal rateOfMonth = rateOfYear.divide(MONTHS_OF_YEAR, MC_4_RATE);
        // （1＋月利率）
        BigDecimal factor = BigDecimal.ONE.add(rateOfMonth);
        // 还款月数
        int months = loanTerm * 12;

        // 被除数
        BigDecimal dividend = factor.pow(months).multiply(loanAmount).multiply(rateOfMonth);

        // 除数
        BigDecimal divisor = factor.pow(months).subtract(BigDecimal.ONE);

        BigDecimal perMonth = dividend.divide(divisor, MC_4_MONEY);
        sum = perMonth.multiply(new BigDecimal(months));

        System.out.println("每月还款：" + perMonth + ", 总还款金额：" + sum);
    }
}
