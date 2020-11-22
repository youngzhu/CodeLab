package com.youngzy.book.concurrency.ch06;

import com.youngzy.book.concurrency.ch05.LaunderThrowable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 6-13 使用Future等待下载图片
 */
public abstract class FutureRender extends BaseRender {
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    void renderPage(CharSequence source) {
        final List<ImageInfo> imageInfos = scanForImageInfo(source);

        Callable<List<ImageData>> task = new Callable<List<ImageData>>() {
            @Override
            public List<ImageData> call() throws Exception {
                List<ImageData> result = new ArrayList<>();
                for (ImageInfo imageInfo : imageInfos) {
                    result.add(imageInfo.downloadImage());
                }
                return result;
            }
        };

        Future<List<ImageData>> future = executorService.submit(task);

        renderText(source);

        try {
            List<ImageData> imageData = future.get();
            for (ImageData data : imageData) {
                renderImage(data);
            }
        } catch (InterruptedException e) {
            // 重新设置线程的中断状态
            Thread.currentThread().interrupt();
            // 不需要结果，取消任务
            future.cancel(true);
        } catch (ExecutionException e) {
            throw LaunderThrowable.launderThrowable(e.getCause());
        }
    }
}
