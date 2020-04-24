package com.youngzy.leetcode.string;

import java.util.List;

/**
 * 给定一个 24 小时制（小时:分钟）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
 * <p>
 * 示例 1：
 * 输入: ["23:59","00:00"]
 * 输出: 1
 * <p>
 * 备注:
 * 列表中时间数在 2~20000 之间。
 * 每个时间取值在 00:00~23:59 之间。
 * <p>
 * 链接：https://leetcode-cn.com/problems/minimum-time-difference
 */
public class MinimumTimeDifference {

    public static final int MINUTES_PER_DAY = 24 * 60;

    /**
     * 别人还有 2 ms 的，再优化看看
     *
     * 拢共分3步:
     *     1 由于分钟数最多为24x60，若时间点个数超过 24x60，则必有重复，最小时间差为0
     *     2 建一个长度为24x60的数组，每一个下标代表一个时间点。遍历入参，将数组相应下标的值+1，如果出现多过一次，就代表有重复，时间差最小为0
     *     3 从步骤2得到一个有序的时间序列，挨个比较，得出最小值
     *
     *      特别要注意的是第一个和最后一个时间点，如 "00:00", "23:59"，需要重新计算一下。
     *
     * @param timePoints
     * @return
     */
    public int findMinDifference(List<String> timePoints) {
        // 由于分钟数最多为24*60，若时间点个数超过24*60，则必有重复，最小时间差为0.
        if (timePoints.size() > MINUTES_PER_DAY) {
            return 0;
        }

        int[] minutesArr = new int[MINUTES_PER_DAY];
        int idx;
        for (String time : timePoints) {
            idx = Integer.valueOf(time.substring(0, 2)) * 60
                    + Integer.valueOf(time.substring(3, 5));

            if (minutesArr[idx] == 1) {
                return 0;
            }

            minutesArr[idx] = 1;
        }

        // 先默认最大值，因为要取小
        int result = MINUTES_PER_DAY;
        int first = -1, pre = -1;

        // 从小到达，挨个比较，得出最小的时间差
        for (int i = 0; i < minutesArr.length; i++) {
            if (minutesArr[i] == 1) {
                if (first == -1) {
                    first = i; // 最小的时间点
                }
                if (pre != -1) {
                    result = Math.min(result, i - pre);
                }
                pre = i;
            }
        }

        // 处理类似 "00:00", "23:59" 的情况
        // 不是 "00:00" ==》 "23:59"
        // 而是 （昨天的）"23:59" ==》 （今天的）"00:00"，相当于多跑了一圈，所以加上 24*60
        result = Math.min(result, MINUTES_PER_DAY + first - pre);

        return result;

    }

    /**
     * 参考学习
     * 时间转化为分钟，存入数组 int[24*60]
     * 一旦int[x]大于0（之前出现过），就说明有一样的分钟数，返回 0
     *
     * 总结：比 0 快了很多，9：117
     *
     * @param timePoints
     * @return
     */
    public int findMinDifference1(List<String> timePoints) {
        // 由于分钟数最多为24*60，若时间点个数超过24*60，则必有重复，最小时间差为0.
        if (timePoints.size() > MINUTES_PER_DAY) {
            return 0;
        }

        int[] minutesArr = new int[MINUTES_PER_DAY];
        int idx;
        for (String time : timePoints) {
           idx = Integer.valueOf(time.substring(0, 2)) * 60
                    + Integer.valueOf(time.substring(3, 5));

           if (minutesArr[idx] > 0) {
               return 0;
           } else {
               minutesArr[idx] = 1;
           }
        }

        // 先默认最大值，因为要取小
        int result = MINUTES_PER_DAY;
        int hourDiff, minuteDiff;

        int hour1, hour2, minute1, minute2;
        int tmpDiff;
        String[] arr1, arr2;
        for (int i = 0; i <= timePoints.size() - 2; i++) {
            arr1 = timePoints.get(i).split(":");
            hour1 = Integer.valueOf(arr1[0]);
            minute1 = Integer.valueOf(arr1[1]);

            for (int j = i + 1; j < timePoints.size(); j++) {

                arr2 = timePoints.get(j).split(":");

                hour2 = Integer.valueOf(arr2[0]);
                minute2 = Integer.valueOf(arr2[1]);

                /*
                不能做简单的加减
                画个圆（钟）
                如果小时数相差超过12，则应该换一个方向去计算
                如，23：59，00：01
                最小差不是 00：01 ~ 23：59
                而是 23：59 ~ 00：01 。此时，小的那个时间应该加一圈 23：60，然后再去加减
                 */
                hourDiff = hour1 - hour2;
                minuteDiff = minute1 - minute2;
                if (Math.abs(hourDiff) > 12) {
                    if (hourDiff < 0) {
                        hourDiff += 23;
                        minuteDiff += 60;
                    } else {
                        hourDiff -= 23;
                        minuteDiff -= 60;
                    }
                }


                tmpDiff = hourDiff * 60 + minuteDiff;
                if (Math.abs(tmpDiff) < result) {
                    result = Math.abs(tmpDiff);
                }
            }
        }

        return result;

    }

    /**
     * 笨方法，挨个比
     * <p>
     * 本以为很简单。做起来还挺复杂
     *
     * @param timePoints
     * @return
     */
    public int findMinDifference0(List<String> timePoints) {
        // 由于分钟数最多为24*60，若时间点个数超过24*60，则必有重复，最小时间差为0.
        if(timePoints.size() > MINUTES_PER_DAY){
            return 0;
        }

        // 先默认最大值，因为要取小
        int result = Integer.MAX_VALUE;
        int hourDiff, minuteDiff;

        int hour1, hour2, minute1, minute2;
        int tmpDiff;
        String[] arr1, arr2;
        for (int i = 0; i <= timePoints.size() - 2; i++) {
            arr1 = timePoints.get(i).split(":");
            hour1 = Integer.valueOf(arr1[0]);
            minute1 = Integer.valueOf(arr1[1]);

            for (int j = i + 1; j < timePoints.size(); j++) {

                arr2 = timePoints.get(j).split(":");

                hour2 = Integer.valueOf(arr2[0]);
                minute2 = Integer.valueOf(arr2[1]);

                /*
                不能做简单的加减
                画个圆（钟）
                如果小时数相差超过12，则应该换一个方向去计算
                如，23：59，00：01
                最小差不是 00：01 ~ 23：59
                而是 23：59 ~ 00：01 。此时，小的那个时间应该加一圈 23：60，然后再去加减
                 */
                hourDiff = hour1 - hour2;
                minuteDiff = minute1 - minute2;
                if (Math.abs(hourDiff) > 12) {
                    if (hourDiff < 0) {
                        hourDiff += 23;
                        minuteDiff += 60;
                    } else {
                        hourDiff -= 23;
                        minuteDiff -= 60;
                    }
                }


                tmpDiff = hourDiff * 60 + minuteDiff;
                if (Math.abs(tmpDiff) < result) {
                    result = Math.abs(tmpDiff);
                }
            }
        }

        return result;
    }
}
