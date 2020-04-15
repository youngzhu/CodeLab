package com.youngzy.lpr;

import com.youngzy.util.ThreadSafeDateUtil;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;

public class Rate {

    private static final MathContext MC_RATE = new MathContext(4);
    private static final MathContext MC_PERCENT = new MathContext(2);

    private final int id;
    private final BigDecimal rate;
    private final Date startDate;
    // 以上字段，对象创建后就不可变

    private Rate pre; // 上一期费率
    private BigDecimal diffRatePercent; // 跟上一期比较的百分比

    public Rate(int id, String rateStr, String startDateStr) {
        this.id = id;
        this.rate = new BigDecimal(rateStr, MC_RATE);
        this.startDate = ThreadSafeDateUtil.parseDate("yyyy.MM.dd", startDateStr);;
    }

    /**
     * 跟前一个相比变化情况
     *
     * 第1次变化，距上次变化 12 个月，上升/下降 0.23
     *
     * @return
     */
    public String getDiff() {
        return this.getDiff(0);
    }

    /**
     * 跟前一个相比变化情况
     *
     * 第1次变化，距上次变化 12 个月，上升/下降 22%
     *
     * @return
     */
    public String getDiffByPercent() {
        return this.getDiff(1);
    }

    /**
     * 跟前一个相比变化情况
     *
     * 第1次变化，距上次变化 12 个月，上升/下降 0.23
     *
     * @return
     */
    private String getDiff(final int type) {
        if (id == 0) {
            return "";
        }

        StringBuilder ret = new StringBuilder();

        int diffDate = ThreadSafeDateUtil.getDiffMonth(pre.getStartDate(), startDate);
        BigDecimal diffRate = rate.subtract(pre.getRate());

        diffRatePercent = diffRate.divide(pre.getRate(), MC_PERCENT);

        ret.append("第 ").append(id).append(" 次变化(")
                .append(ThreadSafeDateUtil.getYear(startDate))
                .append(")，距上次变化")
                .append(diffDate).append("个月，");

        String upOrDown = "下降"; // 大趋势是下降
        if (diffRate.compareTo(BigDecimal.ZERO) > 0) {
            upOrDown = "上升";
        }
        ret.append(upOrDown);

        if (type == 0) {
            ret.append(diffRate);
        } else {
            ret.append("(%)：").append(diffRatePercent.multiply(new BigDecimal(100)));
        }

        return ret.toString();
    }

    public BigDecimal getRate() {
        return rate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setPre(Rate pre) {
        this.pre = pre;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getDiffRatePercent() {
        return diffRatePercent;
    }
}
