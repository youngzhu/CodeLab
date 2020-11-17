package com.youngzy.book.concurrency.ch04;

/**
 * 4-6 不可变的Point
 */
public class Point {
    public final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
