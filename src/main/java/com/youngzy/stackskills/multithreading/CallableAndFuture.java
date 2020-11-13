package com.youngzy.stackskills.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableAndFuture {
    public static void main(String[] args) {
        // 新方法中 不需要 Thread 类了
        // 也不需要手动处理线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        List<Future<Integer>> futureList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            MyCallable callable = new MyCallable(i);
            Future<Integer> future = executorService.submit(callable);
            futureList.add(future);
            System.out.println(callable.id + " is done? " + future.isDone());
        }

        for (Future<Integer> future : futureList) {
            try {
                int result = future.get();
                System.out.println("result=" + result);
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

class MyCallable implements Callable<Integer> {

    private static int c;

    int id;

    public MyCallable(int id) {
        this.id = id;
    }

    /*
        跟 Runnable 比较2个优点：
        1 有返回值，不会与其他线程混淆
        2 可以在主线程中捕获并处理异常
         */
    @Override
    public Integer call() throws Exception {
        if (c <= 1000) {
            c ++;
        }

        // 执行太快，如果休息了 打印出的result 都是 10
//        Thread.sleep(1);

        return c;
    }
}