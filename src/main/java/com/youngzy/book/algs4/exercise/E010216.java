package com.youngzy.book.algs4.exercise;

import edu.princeton.cs.algs4.StdOut;

import java.util.Objects;

/**
 * 有理数
 *
 * @author youngzy
 * @since 2021-07-24
 */
public class E010216 {

    /**
     * https://algs4.cs.princeton.edu/12oop/Rational.java.html
     *
     * 不可变的抽象数据类型（ADT）—— 有理数
     *
     * 不变性：
     * 1. gcd(num, den)=1，有理数的简化形式
     * 2. den >= 1，分母永远是正整数
     * 3. 0/1 是0的唯一表示形式
     *
     * 我们使用了一些技巧来避免溢出，如果需要高精度的有理数，可使用 BigRational.java
     *
     */
    static class Rational implements Comparable<Rational> {
        private static final Rational ZERO = new Rational(0, 1);

        private long num; // 分子
        private long den; // 分母

        Rational(long numerator, long denominator) { // 分子，分母

            // 分母为0
            if (denominator == 0) {
                throw new ArithmeticException("denominator is 0");
            }

            // 简化
            long g = gcd(numerator, denominator);
            numerator = numerator / g;
            denominator = denominator / g;

            // 处理负数
            if (denominator < 0) {
                denominator = -denominator;
                numerator = - numerator;
            }

            den = denominator;
            num = numerator;
        }

        public long numerator() {
            return num;
        }
        public long denominator() {
            return den;
        }

        public double toDouble() {
            return (double) num / den;
        }

        @Override
        public String toString() {
            if (den == 1)
                return num + "";
            else
                return num + "/" + den;
        }

        /**
         * @return
         *  -1 <
         *  0 =
         *  * 1 >
         */
        @Override
        public int compareTo(Rational that) {
            long lhs = this.num * that.den;
            long rhs = this.den * that.num;
            if (lhs < rhs) return -1;
            if (lhs > rhs) return +1;
            return 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Rational rational = (Rational) o;
            return compareTo(rational) == 0;
        }

        @Override
        public int hashCode() {
            return this.toString().hashCode();
        }

        // 创建一个新的有理数 (r.num + s.num)/(r.den + s.den)
        public static Rational mediant(Rational r, Rational s) {
            return new Rational(r.num + s.num, r.den + s.den);
        }

        // 最大公约数
        // gcd(|m|, |n|)
        private static long gcd(long m, long n) {
            if (m < 0) m = -m;
            if (n < 0) n = -n;
            if (n == 0)
                return m;
            else
                return gcd(n, m % n);
        }

        private static long lcm(long m, long n) {
            if (m < 0) m = -m;
            if (n < 0) n = -n;
            return m * (n / gcd(m, n)); // 括号对避免溢出非常重要
        }

        public Rational times(Rational that) {
            // 通过交换，来减少溢出的可能性
            // a = p1/q1, b= p2/q2 由于构造函数，已经是简化过的，gcd
            // c = p1/q2, d = p2/q1 交叉，再次通过构造函数简化
            Rational c = new Rational(this.num, that.den);
            Rational d = new Rational(that.num, this.den);
            return new Rational(c.num * d.num, c.den * d.den);
        }

        public Rational plus(Rational that) {
            if (this.compareTo(ZERO) == 0) return that;
            if (that.compareTo(ZERO) == 0) return this;

            long f = gcd(this.num, that.num);
            long g = gcd(this.den, that.den);

            Rational s= new Rational((this.num / f) * (that.den / g)
                                        + (that.num / f) * (this.den / g),
                    this.den * (that.den / g));

            s.num *= f;
            return s;
        }

        public Rational negate() {
            return new Rational(-num, den);
        }

        public Rational abs() {
            if (num >= 0)
                return this;
            else
                return negate();
        }

        public Rational minus(Rational that) {
            return this.plus(that.negate());
        }

        // 倒数
        public Rational reciprocal() {
            return new Rational(den, num);
        }

        // this/that
        public Rational divide(Rational that) {
            return this.times(that.reciprocal());
        }

        public static void main(String[] args) {
            Rational x, y, z;

            // 1/2 + 1/3 = 5/6
            x = new Rational(1, 2);
            y = new Rational(1, 3);
            z = x.plus(y);
            StdOut.println(z);

            // 8/9 + 1/9 = 1
            x = new Rational(8, 9);
            y = new Rational(1, 9);
            z = x.plus(y);
            StdOut.println(z);

            // 1/200000000 + 1/300000000 = 1/120000000
            x = new Rational(1, 200000000);
            y = new Rational(1, 300000000);
            z = x.plus(y);
            StdOut.println(z);

            // 1073741789/20 + 1073741789/30 = 1073741789/12
            x = new Rational(1073741789, 20);
            y = new Rational(1073741789, 30);
            z = x.plus(y);
            StdOut.println(z);

            //  4/17 * 17/4 = 1
            x = new Rational(4, 17);
            y = new Rational(17, 4);
            z = x.times(y);
            StdOut.println(z);

            // 3037141/3247033 * 3037547/3246599 = 841/961
            x = new Rational(3037141, 3247033);
            y = new Rational(3037547, 3246599);
            z = x.times(y);
            StdOut.println(z);

            // 1/6 - -4/-8 = -1/3
            x = new Rational(1, 6);
            y = new Rational(-4, -8);
            z = x.minus(y);
            StdOut.println(z);
        }
    }
}
