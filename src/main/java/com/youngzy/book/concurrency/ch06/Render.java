package com.youngzy.book.concurrency.ch06;

import com.youngzy.book.concurrency.ch05.LaunderThrowable;

import java.awt.image.ImagingOpException;
import java.util.List;
import java.util.concurrent.*;

/**
 * 6-15 使用 CompletionService 使页面元素在加载完成后立即显示出来
 */
public abstract class Render extends BaseRender {
    private final ExecutorService executorService;

    Render(ExecutorService executorService) {
        this.executorService = executorService;
    }

    void renderPage(CharSequence source) {
        final List<ImageInfo> imageInfoList = scanForImageInfo(source);

        CompletionService<ImageData> completionService = new ExecutorCompletionService<>(executorService);

        for (final ImageInfo imageInfo : imageInfoList) {
            completionService.submit(new Callable<ImageData>() {
                @Override
                public ImageData call() throws Exception {
                    return imageInfo.downloadImage();
                }
            });
        }

        renderText(source);

        try {
            for (int i = 0, n = imageInfoList.size(); i < n; i++) {
                Future<ImageData> future = completionService.take();
                ImageData imageData = future.get();
                renderImage(imageData);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw LaunderThrowable.launderThrowable(e.getCause());
        }
    }
}
