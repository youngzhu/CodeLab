package com.youngzy.book.concurrency.ch07;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 7-20 使用私有的Executor，其生命周期受限于方法调用
 *
 * 如果某个方法需要处理一批任务，并且当所有任务都处理完成后才返回，
 * 那么可以通过一个私有的Executor来简化服务的生命周期管理，交由这个方法来控制
 */
public class CheckForMail {
    public boolean checkMail(Set<String> hosts, long timeout, TimeUnit unit)
            throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 之所以用AtomicBoolean 来代替 volatile，
        // 是因为要从内部的Runnable中访问
        // 因此它必须是final类型，以免被修改
        final AtomicBoolean hasNewMail = new AtomicBoolean(false);
        try {
            for (final String host : hosts) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        if (checkMail(host))
                            hasNewMail.set(true);
                    }
                });
            }
        } finally {
            executorService.shutdown();
            executorService.awaitTermination(timeout, unit);
        }
        return hasNewMail.get();
    }

    private boolean checkMail(String host) {
        // check for mail
        return false;
    }
}
