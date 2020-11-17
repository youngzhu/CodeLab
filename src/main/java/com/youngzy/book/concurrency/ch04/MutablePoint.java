package com.youngzy.book.concurrency.ch04;

/**
 * 4-5 可变的Point类（不要这么做）
 */
public class MutablePoint {
    public int x, y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint point) {
        this.x = point.x;
        this.y = point.y;
    }
}
