package com.youngzy.book.concurrency.ch08;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 8-1 在单线程Executor中任务发生死锁（不要这么做）
 *
 */
public class ThreadDeadLock {
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    public class LoadFileTask implements Callable<String> {
        private final String fileName;

        public LoadFileTask(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public String call() throws Exception {
            // 读取文件
            return "";
        }
    }

    public class RenderPageTask implements Callable<String> {
        @Override
        public String call() throws Exception {
            Future<String> header, footer;
            header = executorService.submit(new LoadFileTask("header.html"));
            footer = executorService.submit(new LoadFileTask("footer.html"));
            String page = renderBody();
            // 由于任务在等待子任务的结果，将发生死锁
            return header.get() + page + footer.get();
        }

        private String renderBody() {
            // 渲染页面主体
            return "";
        }
    }
}
