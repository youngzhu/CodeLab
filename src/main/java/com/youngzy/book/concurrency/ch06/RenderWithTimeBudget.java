package com.youngzy.book.concurrency.ch06;

import java.util.concurrent.*;

/**
 * 6-16 在预定时间内获取广告信息
 */
public class RenderWithTimeBudget {
    private static final Ad DEFAULT_AD = new Ad();
    private static final long time_budget = 1000;
    private static final ExecutorService exec = Executors.newCachedThreadPool();

    Page renderPageWithAd() throws InterruptedException {
        long endNano = System.nanoTime() + time_budget;
        Future<Ad> future = exec.submit(new FetchAdTask());

        // 在等待广告的同时渲染页面
        Page page = renderPageBody();

        Ad ad;
        try {
            // 只等待指定的时间长度
            long timeLeft = endNano - System.nanoTime();
            ad = future.get(timeLeft, TimeUnit.NANOSECONDS);
        } catch (ExecutionException e) {
            ad = DEFAULT_AD;
        } catch (TimeoutException e) {
            ad = DEFAULT_AD;
            future.cancel(true);
        }

        page.setAd(ad);

        return page;
    }

    private Page renderPageBody() {
        return new Page();
    }

    static class FetchAdTask implements Callable<Ad> {
        @Override
        public Ad call() throws Exception {
            return new Ad();
        }
    }

    static class Page {
        void setAd(Ad ad) {}
    }
    static class Ad {}
}
