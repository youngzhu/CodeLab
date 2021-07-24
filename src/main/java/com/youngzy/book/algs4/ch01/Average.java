package com.youngzy.book.algs4.ch01;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * StdIn 示例
 * @author youngzy
 * @since 2021-07-13
 */
public class Average {
    public static void main(String[] args) {
        // 取StdIn中所有数据的平均值
        double sum = 0.0;
        int cnt = 0;
        // Mac: CMD - D 结束输入
        while (!StdIn.isEmpty()) {
            sum += StdIn.readDouble();
            cnt++;
        }
        double avg = sum / cnt;
        StdOut.printf("Average is %.5f\n", avg);
    }
}
