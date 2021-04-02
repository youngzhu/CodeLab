package com.youngzy.book.concurrency.exercise.ex01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Checker {
    private static final int MAX_ALERT_RECORDS = 10;
    private static final int MAX_SLEEP_MILLIS = 5;

    /**
     * 校验重复数据
     * 最多给出10组提醒，多的用“等”代替
     *
     * @param data
     * @return
     */
    public String checkDuplication(int[] data) {
        long start = System.currentTimeMillis();

        /*
        list, set 差别不大
                    100 1000    10000   100000
         list       299 1286    13717   145714
         hashset    378 1895    14110   137264
         treeset    321 1519    13773   140513
         */
//        Set<Integer> uniqueData = new HashSet<>(data.length);
//        Set<Integer> uniqueData = new TreeSet<>();
//        Set<Integer> alerts = new HashSet<>(MAX_ALERT_RECORDS);
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

//        System.out.println(data.length + "-重复校验耗时-" + (System.currentTimeMillis() - start));
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
    public String checkRange(int[] data, int low, int high) {
        long start = System.currentTimeMillis();

        String result = "";

        List<Integer> outRange = new ArrayList<>();

        int counter = 0; // 总的重复数
        int alertCounter = 0;
        for (int num : data) {
            if (num < low || num > high) {
                if (alertCounter < MAX_ALERT_RECORDS && ! outRange.contains(num)) {
                    outRange.add(alertCounter ++, num);
                }
                counter ++;
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
            if (counter > MAX_ALERT_RECORDS) {
                result += "等" + counter + "个";
            }
            result += "超出范围";
        }

        System.out.println(data.length + "-范围校验耗时-" + (System.currentTimeMillis() - start));
        return result;
    }
}
