package com.youngzy.book.concurrency.exercise.ex01;

import java.util.*;
import java.util.concurrent.*;

/**
 * 一个线程处理多条数据
 *
 * 10 条
 * 100-并行校验耗时-275
 * 500-并行校验耗时-830
 * 1000-并行校验耗时-1427
 * 10000-并行校验耗时-13740
 *
 * 1 条
 * 100-并行校验耗时-221
 * 500-并行校验耗时-734
 * 1000-并行校验耗时-2029
 * 10000-并行校验耗时-13968
 *
 * 100 条
 * 100-并行校验耗时-308
 * 500-并行校验耗时-731
 * 1000-并行校验耗时-1423
 * 10000-并行校验耗时-13827
 *
 * CPUs
 * 100-并行校验耗时-444
 * 500-并行校验耗时-1574
 * 1000-并行校验耗时-1371
 * 10000-并行校验耗时-14083
 *
 */
public class ConcurrentCheckerV1 {
    private static final int MAX_ALERT_RECORDS = 10;
    private static final int MAX_SLEEP_MILLIS = 5;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    public List<String> check(final int data[], int min, int max) {
        long start = System.currentTimeMillis();

        CompletionService<Set<Integer>> completionService =
                new ExecutorCompletionService<>(executorService);

        int datasPerTask = 1; // 每个任务的数据量

        for (int i = 0; i < data.length / datasPerTask; i ++) {
            int from = i * datasPerTask;
            int to = Math.min(from + datasPerTask - 1, data.length - 1);

            completionService.submit(new CheckCallable(data, from, to, min, max));
        }

        String result1 = checkDuplication(data);
        Set<Integer> list2 = new HashSet<>();

        try {
            for (int i = 0; i < data.length / datasPerTask; i ++) {
                Future<Set<Integer>> future = completionService.take();
                Set<Integer> result = future.get();
                if (!result.isEmpty())
                    list2.addAll(result);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {

        }

        String result2 = list2.toString() + "超出范围";

        System.out.println(data.length + "-并行（批量）校验耗时-" + (System.currentTimeMillis() - start));

        List<String> list = new ArrayList<>();
        list.add(result1);
        list.add(result2);
        return list;
    }


    private class CheckCallable implements Callable<Set<Integer>> {
        final int[] checkData;
        final int from;
        final int to;
        final int min;
        final int max;

        CheckCallable(int[] checkData, int from, int to, int min, int max) {
            this.checkData = checkData;
            this.from = from;
            this.to = to;
            this.min = min;
            this.max = max;
        }

        @Override
        public Set<Integer> call() throws Exception {
            Set<Integer> set = new HashSet<>();
            for (int i = from; i <= to; i ++) {
                if (! checkRange(checkData[i], min, max))
                    set.add(checkData[i]);
            }

            return set;
        }
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
