package com.youngzy.book.concurrency.ch06;


import java.util.ArrayList;
import java.util.List;

/**
 * 6-10 串行地渲染页面元素
 */
public abstract class SingleThreadRender extends BaseRender {
    void renderPage(CharSequence source) {
        renderText(source);
        List<ImageData> imageData = new ArrayList<>();
        for (ImageInfo imageInfo : scanForImageInfo(source))
            imageData.add(imageInfo.downloadImage());
        
        for (ImageData data : imageData) {
            renderImage(data);
        }
    }
}
