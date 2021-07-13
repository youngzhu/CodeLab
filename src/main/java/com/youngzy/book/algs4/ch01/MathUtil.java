package com.youngzy.book.algs4.ch01;

public class MathUtil {
    /**
     * 计算两个非负整数p和q的最大公约数
     * 若q=0，则最大公约数为p
     * 否则，p除以q得到的余数r，p和q的最大公约数即为q和r的最大公约数
     *
     * @param p
     * @param q
     * @return
     */
    public static int gcd(int p, int q) {
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }

    /**
     * 计算绝对值
     *
     * @param x
     * @return
     */
    public static int abs(int x) {
        if (x < 0)
            return -x;
        else
            return x;
    }

    /**
     * 判断一个数是否为素数
     *
     * @param x
     * @return
     */
    public static boolean isPrime(int x) {
        if (x < 2)
            return false;
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0)
                return false;
        }
        return true;
    }

    /**
     * 计算平方根（牛顿迭代法）
     *
     * @param x
     * @return
     */
    public static double sqrt(double x) {
        if (x < 0)
            return Double.NaN;
        double err = 1e-15;
        double t = x;
        while (Math.abs(t - x / t) > err * t)
            t = (x / t + t) / 2;
        return t;
    }

    /**
     * 计算直角三角形的斜边
     *
     * @param a
     * @param b
     * @return
     */
    public static double hypotenuse(double a, double b) {
        return Math.sqrt(a * a + b * b);
    }

    /**
     * 计算调和级数
     *
     * @param x
     * @return
     */
    public static double H(int x) {
        double sum = 0;
        for (int i = 1; i <= x; i ++) {
            sum += 1.0 / i;
        }
        return sum;
    }
}
