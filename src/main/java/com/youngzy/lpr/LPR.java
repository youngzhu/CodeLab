package com.youngzy.lpr;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

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
    private BigDecimal baseRate; // 基准利率

    public LPR(BigDecimal loanAmount, int loanTerm, BigDecimal rateOfYear, BigDecimal baseRate) {
        this.loanAmount = loanAmount;
        this.loanTerm = loanTerm;
        this.rateOfYear = rateOfYear.multiply(new BigDecimal(0.01));
        this.baseRate = baseRate.multiply(new BigDecimal(0.01));
    }

    /**
     * lpr的变化趋势
     * 完全复制过去20次的变动幅度
     */
    public void lprTrending() {
        List<Rate> rateList = loadRate();
    }

    /**
     * 基准利率的变化趋势
     */
    public void rateTrending() {
        List<Rate> rateList = loadRate();

//        diffRate(rateList);
        diffRateByPercent(rateList);
    }

    /**
     * 按数值显示费率变化情况
     *
     * @param rateList
     */
    private void diffRate(List<Rate> rateList) {
        for (Rate tmp : rateList) {
            System.out.println(tmp.getDiff());
        }
    }

    /**
     * 按百分比显示费率变化情况
     *
     * @param rateList
     */
    private void diffRateByPercent(List<Rate> rateList) {
        for (Rate tmp : rateList) {
            System.out.println(tmp.getDiffByPercent());
        }
    }

    private List<Rate> loadRate() {
        List<Rate> ret = new ArrayList<Rate>();

        Rate cur, pre = null;

        int i = 0;
        for (String[] tmp : Constant.LOAN_RATE_5_YEARS) {
            cur = new Rate(i++, tmp[1], tmp[0]);

            if (pre != null) {
                cur.setPre(pre);
            }

            pre = cur;
            ret.add(cur);
        }

        return ret;
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
        // 还款月数
        int months = loanTerm * 12;
        BigDecimal sum  = payPerMonth().multiply(new BigDecimal(months));

        System.out.println("总还款金额：" + sum);
        return sum;
    }

    /**
     * 等额本息，每月还款额
     *
     * @return
     */
    public BigDecimal payPerMonth() {
        BigDecimal perMonth = BigDecimal.ZERO;

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

        perMonth = dividend.divide(divisor, MC_4_MONEY);

        System.out.println("每月还款：" + perMonth);

        return perMonth;
    }
}
