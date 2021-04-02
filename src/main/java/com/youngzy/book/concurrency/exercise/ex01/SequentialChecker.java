package com.youngzy.book.concurrency.exercise.ex01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SequentialChecker {
    private static final int MAX_ALERT_RECORDS = 10;
    private static final int MAX_SLEEP_MILLIS = 5;

    public List<String> check(int[] data, int min, int max) {
        long start = System.currentTimeMillis();

        String result1 = checkDuplication(data);
        String result2 = checkRange(data, min, max);

        System.out.println(data.length + "-串行校验耗时-" + (System.currentTimeMillis() - start));

        List<String> list = new ArrayList<>();
        list.add(result1);
        list.add(result2);
        return list;
    }

    /**
     * 校验重复数据
     *
     * @param data
     * @return
     */
    private String checkDuplication(int[] data) {
        long start = System.currentTimeMillis();

        List<Integer> uniqueData = new ArrayList<>(data.length);
        List<Integer> alerts = new ArrayList<>(MAX_ALERT_RECORDS);

        String result = "";

        int counter = 0;
        for (int num : data) {
            if (uniqueData.contains(num)) {
                if (alerts.size() < MAX_ALERT_RECORDS) {
                    if (!alerts.contains(num)) {
                        alerts.add(num);
                        counter++;
                    }
                }
            } else {
                uniqueData.add(num);

                // 时常太短，看不出差别
                // 加入随机休眠
                try {
                    Thread.sleep(new Random().nextInt(MAX_SLEEP_MILLIS));
                } catch (InterruptedException e) {
                }
            }
        }

        if (alerts.size() > 0) {
            result = "存在重复数据：";
            result += alerts.toString();
            if (counter > MAX_ALERT_RECORDS) {
                result += "等";
            }
        }

        return result;
    }

    /**
     * 校验数据的范围
     *
     * @param data
     * @param low
     * @param high
     * @return
     */
    private String checkRange(int[] data, int low, int high) {
        String result = "";

        List<Integer> outRange = new ArrayList<>();

        int counter = 0; // 总的重复数
        for (int num : data) {
            if (! checkRange(num, low, high)) {
                if (! outRange.contains(num)) {
                    outRange.add(counter ++, num);
                }
            }

            // 时常太短，看不出差别
            // 加入随机休眠
            try {
                Thread.sleep(new Random().nextInt(MAX_SLEEP_MILLIS));
            } catch (InterruptedException e) {
            }
        }

        if (counter > 0) {
            result = outRange.toString();
            result += "超出范围";
        }

        return result;
    }

    private boolean checkRange(int num, int low, int high) {
        if (num >= low && num <= high)
            return true;
        else
            return false;
    }
}
