package com.youngzy.book.concurrency.ch07;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 7-22 使用TrackingExecutor来保存未完成的任务以备后续执行
 */
public abstract class WebCrawler {
    private volatile TrackingExecutor executor;
    private final Set<URL> urlsToCrawl = new HashSet<>();

    private final ConcurrentMap<URL, Boolean> seen = new ConcurrentHashMap<>();
    private static final long TIMEOUT = 500;
    private static final TimeUnit UNIT = TimeUnit.MILLISECONDS;

    public WebCrawler(URL startUrl) {
        urlsToCrawl.add(startUrl);
    }

    public synchronized void start() {
        executor = new TrackingExecutor(Executors.newCachedThreadPool());
        for (URL url : urlsToCrawl)
            submitCrawlTask(url);
        urlsToCrawl.clear();
    }

    public synchronized void stop() throws InterruptedException {
        try {
            saveUncrawled(executor.shutdownNow());
            if (executor.awaitTermination(TIMEOUT, UNIT))
                saveUncrawled(executor.getCancelledTasks());
        } finally {
            executor = null;
        }
    }

    protected abstract List<URL> processPage(URL url);

    private void saveUncrawled(List<Runnable> uncrawled) {
        for (Runnable task : uncrawled)
            urlsToCrawl.add(((CrawlTask)task).getPage());
    }

    private void submitCrawlTask(URL url) {
        executor.execute(new CrawlTask(url));
    }

    private class CrawlTask implements Runnable {
        private final URL url;

        CrawlTask(URL url) {
            this.url = url;
        }

        private int count = 1;

        boolean alreadyCrawled() {
            return seen.putIfAbsent(url, true) != null;
        }

        void markUncrawled() {
            seen.remove(url);
            System.out.printf("marking %s uncrawled%n", url);
        }

        @Override
        public void run() {
            for (URL url : processPage(url)) {
                if (Thread.currentThread().isInterrupted())
                    return;
                submitCrawlTask(url);
            }
        }

        public URL getPage() {
            return url;
        }
    }
}
