package com.youngzy.book.concurrency.exercise.ex01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Copy V0，将重复校验并发
 */
public class ConcurrentCheckerV2 {
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private ConcurrentMap<Integer, Boolean> uniqueDataMap = new ConcurrentHashMap();
    private ConcurrentMap<Integer, Boolean> duplicateDataMap = new ConcurrentHashMap();


    public List<String> check(int data[], int min, int max) {
        long start = System.currentTimeMillis();

        CompletionService<Void> duplicateService = new ExecutorCompletionService<>(executorService);

        CompletionService<Integer> rangeService =
                new ExecutorCompletionService<>(executorService);


        for (final int num : data) {
            duplicateService.submit(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    if (uniqueDataMap.putIfAbsent(num, true) != null) {
                        duplicateDataMap.putIfAbsent(num, true);
                    }
                    return null;
                }
            });
        }
        for (final int num : data) {
            rangeService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    if (checkRange(num, min, max))
                        return null;
                    else
                        return num;
                }
            });
        }

        String result1 = duplicateDataMap.keySet().toString();
        List<Integer> list2 = new ArrayList<>();

        try {
            for (int i = 0; i < data.length; i++) {
                Future<Integer> future = rangeService.take();
                Integer result = future.get();
                if (result != null && ! list2.contains(result))
                    list2.add(result);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {

        } finally {
            // 不应该在这里关闭，因为 check 方法可能调多次
            // 所以，executorService也不应该在Checker类中新建，
            // 而应该作为一个参数传入
//            executorService.shutdown();
        }

        String result2 = list2.toString() + "超出范围";

        System.out.println(data.length + "-两条并行校验耗时-" + (System.currentTimeMillis() - start));

        List<String> list = new ArrayList<>();
        list.add(result1);
        list.add(result2);
        return list;
    }

    private boolean checkRange(int num, int low, int high) {
        if (num >= low && num <= high)
            return true;
        else
            return false;
    }
}
