package com.youngzy.stackskills.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableAndFuture2 {
    /**
     * v1 版本有个问题：各个线程都是线性执行的，即必须等前面的执行完了才轮到自己，
     * 即使本线程早完成了
     * 所以引入了一个新的类：CompletionService
     *
     * 从打印结果上看，这个更无序，因为是先完成的先打印
     *
     * @param args
     */
    public static void main(String[] args) {
        // 新方法中 不需要 Thread 类了
        // 也不需要手动处理线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);

        List<Future<Integer>> futureList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            MyCallable callable = new MyCallable(i);
            completionService.submit(callable);
        }

        for (int i = 0; i < 100; i++) {
            try {
                // take() 总是返回最近结束的线程（队列）
                int result = completionService.take().get();
                System.out.println("result = " + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        // 执行完成，关闭线程池
        executorService.shutdown();
    }
}

class MyCallable2 implements Callable<Integer> {

    private static int c;

    int id;

    public MyCallable2(int id) {
        this.id = id;
    }

    /*
        跟 Runnable 比较2个优点：
        1 有返回值，不会与其他线程混淆
        2 可以在主线程中捕获并处理异常
         */
    @Override
    public Integer call() throws Exception {
        // 要做的事就是 +1 跟上限无关
//        if (c <= 2000) {
            c ++;
//        }

        // 执行太快，如果休息了 打印出的result 都是 10
//        Thread.sleep(1);

        return c;
    }
}