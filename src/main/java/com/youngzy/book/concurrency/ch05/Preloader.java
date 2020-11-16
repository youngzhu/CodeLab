package com.youngzy.book.concurrency.ch05;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 5-12 使用 FutureTask 来提前加载稍后需要的数据
 */
public class Preloader {
    ProductInfo loadProductInfo() throws DataLoadException {
        return null;
    }

    private final FutureTask<ProductInfo> futureTask =
            new FutureTask<>(new Callable<ProductInfo>() {
                @Override
                public ProductInfo call() throws Exception {
                    return loadProductInfo();
                }
            });

    private final Thread thread = new Thread(futureTask);

    public void start() {
        thread.start();
    }

    public ProductInfo get() throws DataLoadException, InterruptedException {
        try {
            return futureTask.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof DataLoadException) {
                throw (DataLoadException) cause;
            } else {
                throw LaunderThrowable.launderThrowable(cause);
            }
        }
    }
    interface ProductInfo {}
}

class DataLoadException extends Exception {}
