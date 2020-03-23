package com.youngzy.lpr;

import com.youngzy.util.ThreadSafeDateUtil;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Date;

/**
 *
 *
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
     * 基准利率的变化趋势
     */
    public void rateTrending() {
        Date preDate = null, curDate;
        BigDecimal preRate = null, curRate;

        int diffDate = 0; // 时间间隔
        BigDecimal diffRate = BigDecimal.ZERO; // 利率变化

        for (String[] tmp : Constant.LOAN_RATE_5_YEARS) {
            curDate = ThreadSafeDateUtil.parseDate("yyyy.MM.dd", tmp[0]);
            curRate = new BigDecimal(tmp[1], new MathContext(3));

            if (preDate != null) {
                diffDate = ThreadSafeDateUtil.getDiffMonth(preDate, curDate);
                diffRate = curRate.subtract(preRate);
            }

            System.out.println(tmp[0] + " - " + diffDate + " 个月后：" + diffRate);

            preDate = curDate;
            preRate = curRate;
        }

    }

    /**
     * 等额本息 和 等额本金 的差别
     */
    public void diff() {
        BigDecimal sum1 = calculate1();
        BigDecimal sum2 = calculate2();

        BigDecimal diff = sum1.subtract(sum2);
        BigDecimal perMonth = diff.divide(new BigDecimal(loanTerm * 12), MC_4_MONEY);

        System.out.println("等额本息 比 等额本金 多付利息：" + diff + "，平均每月：" + perMonth);

    }

    /**
     * average capital
     * 等额本金：
     *      每月还款金额 = （贷款本金 / 还款月数）+（本金 — 已归还本金累计额）×每月利率
     */
    public BigDecimal calculate2() {
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal perMonth;

        // 月利率
        BigDecimal rateOfMonth = rateOfYear.divide(MONTHS_OF_YEAR, MC_4_RATE);
        // 还款月数
        int months = loanTerm * 12;

        // 不变的部分
        // 本金=（贷款本金 / 还款月数）
        BigDecimal capital = loanAmount.divide(new BigDecimal(months), MC_4_MONEY);
        // 利息=（本金 — 已归还本金累计额）×每月利率
        BigDecimal interest;
        for (int i = 1; i <= months; i ++) {
            BigDecimal paidLoan = capital.multiply(new BigDecimal(i)); // 已还的本金
            interest = loanAmount.subtract(paidLoan).multiply(rateOfMonth);

            perMonth = capital.add(interest, MC_4_MONEY);

            System.out.println(i + "每月还款：" + perMonth);

            sum = sum.add(perMonth);
        }

        System.out.println(", 总还款金额：" + sum);
        return sum;
    }

    /**
     * average capital plus interest
     * 等额本息：
     *   〔贷款本金×月利率×（1＋月利率）＾还款月数〕÷〔（1＋月利率）＾还款月数－1〕
     */
    public BigDecimal calculate1() {
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
        return sum;
    }
}
