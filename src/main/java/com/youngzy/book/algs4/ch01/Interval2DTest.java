package com.youngzy.book.algs4.ch01;

import com.youngzy.book.concurrency.ch04.Point;
import edu.princeton.cs.algs4.*;

/**
 * Interval2D 的测试用例
 * @author youngzy
 * @since 2021-07-31
 */
public class Interval2DTest {
    public static void main(String[] args) {
        StdOut.println("Enter X low:");
        double xlo = StdIn.readDouble();
        StdOut.println("Enter X high:");
        double xhi = StdIn.readDouble();
        StdOut.println("Enter Y low:");
        double ylo = StdIn.readDouble();
        StdOut.println("Enter Y high:");
        double yhi = StdIn.readDouble();

        StdOut.println("Enter num of Points:");
        int N = StdIn.readInt();

        Interval1D xinterval = new Interval1D(xlo, xhi);
        Interval1D yinterval = new Interval1D(ylo, yhi);
        Interval2D box = new Interval2D(xinterval, yinterval);
        box.draw();

        Counter c = new Counter("hits");
        for (int i = 0; i < N; i++) {
            double x = Math.random();
            double y = Math.random();
            Point2D p = new Point2D(x, y);
            if (box.contains(p))
                c.increment();
            else
                p.draw();
        }

        StdOut.println(c);
        StdOut.println(box.area());
    }
}
