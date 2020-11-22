package com.youngzy.book.concurrency.ch06;


import java.util.ArrayList;
import java.util.List;

/**
 * 6-10 串行地渲染页面元素
 */
public abstract class BaseRender {

    protected abstract void renderImage(ImageData data);

    protected abstract List<ImageInfo> scanForImageInfo(CharSequence source);

    interface ImageData {}
    interface ImageInfo {
        ImageData downloadImage();
    }

    protected abstract void renderText(CharSequence source);
}
