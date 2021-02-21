package com.youngzy.book.pragprog.ch02.dry;

/*
数据中的重复
 */
public class DataDRY {
}

/*
看上去挺有道理，一条线有起点和终点，也有长度。
不过这里有了重复：长度是由起点和终点计算出来的
所以应该把长度作为一个通过计算获得的字段，见Line2
 */
class Line1 {
    Point start;
    Point end;
    double length;
}

class Line2 {
    Point start;
    Point end;
    double length() {
        return start.distanceTo(end);
    }
}

class Line3 {
    private Point start;
    private Point end;
    private double length;

    public Line3(Point start, Point end) {
        this.start = start;
        this.end = end;
        calculateLength();
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
        calculateLength();
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
        calculateLength();
    }

    /*
    不用每次都计算
    使用缓存，可以避免代价高昂的计算
     */
    public double getLength() {
        return length;
    }

    private void calculateLength() {
        this.length = start.distanceTo(end);
    }
}

interface Point {
    double distanceTo(Point p);
}
