package com.youngzy.book.concurrency.exercise.ex01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ConcurrentCheckerV0 {
    private static final int MAX_ALERT_RECORDS = 10;
    private static final int MAX_SLEEP_MILLIS = 5;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    /*
    每一条记录一个线程

    效果还行，大概3倍吧

    100-并行校验耗时-236 x1.5
    100-串行校验耗时-394
    1000-并行校验耗时-1305 x2.8
    1000-串行校验耗时-3632
    10000-并行校验耗时-13934 x2.6
    10000-串行校验耗时-35989
    100000-并行校验耗时-150009 x2.4
    100000-串行校验耗时-363671
     */
    public List<String> check(int data[], int min, int max) {
        long start = System.currentTimeMillis();

        CompletionService<Integer> completionService =
                new ExecutorCompletionService<>(executorService);

        for (final int num : data) {
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    if (checkRange(num, min, max))
                        return null;
                    else
                        return num;
                }
            });
        }

        String result1 = checkDuplication(data);
        List<Integer> list2 = new ArrayList<>();

        try {
            // 没有多大差别
            // 测试耗时忽上忽下的
//            for (int i = 0; i < data.length; i++) {
            for (int i = 0, n = data.length; i < n; i++) {
                Future<Integer> future = completionService.take();
                Integer result = future.get();
                if (result != null && ! list2.contains(result))
                    list2.add(result);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {

        }

        String result2 = list2.toString() + "超出范围";

        System.out.println(data.length + "-并行校验耗时-" + (System.currentTimeMillis() - start));

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

    private boolean checkRange(int num, int low, int high) {
        if (num >= low && num <= high)
            return true;
        else
            return false;
    }
}
