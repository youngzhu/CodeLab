package com.youngzy.book.concurrency.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 对一组数据做2个校验：
 * 1. 是否有重复数据
 * 2. 是否超出了指定范围
 */
public class Exercise01 {
    private static final int MAX_ALERT_RECORDS = 10;

    /**
     * 校验重复数据
     * 最多给出10组提醒，多的用“等”代替
     *
     * @param data
     * @return
     */
    public String checkDuplication(int[] data) {

        return null;
    }

    /**
     * 校验数据的范围
     *
     * @param data
     * @param low
     * @param high
     * @return
     */
    public String checkRange(int[] data, int low, int high) {
        long start = System.currentTimeMillis();

        String result = "";

        List<Integer> outRange = new ArrayList<>(MAX_ALERT_RECORDS);

        int counter = 0;
        for (int num : data) {
            if (num < low || num > high) {
                if (counter < MAX_ALERT_RECORDS) {
                    outRange.add(counter, num);
                }
                counter++;

                // 时常太短，看不出差别
                // 加入随机休眠
                try {
                    Thread.sleep(new Random().nextInt(100));
                } catch (InterruptedException e) {
                }
            }
        }

        if (counter > 0) {
            result = outRange.toString();
            if (counter > MAX_ALERT_RECORDS) {
                result += "等";
            }
            result += "超出范围";
        }

        System.out.println(data.length + "-耗时-" + (System.currentTimeMillis() - start));
        return result;
    }
}
