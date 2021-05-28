package com.youngzy.book.concurrency;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Template {
    public void processInParallel(List<Object> list) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        try {
            for (final Object obj : list) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        // do process
                    }
                });
            }
        } finally {
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        }

    }
}
