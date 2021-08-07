package com.youngzy.book.algs4.exercise;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 从标准输入按行读取数据，其中每行都包含一个名字和两个整数。
 * 用printf打印一张表格，每行的若干列数据包括名字、两个整数和第一个整数除以第二个整数的结果，精确到小数点后3位
 *
 * @author youngzy
 * @since 2021-07-24
 */
public class E010121 {
    public static void main(String[] args) {
        In in = new In("algs4/e010121.txt");
        while (in.hasNextLine()) {
//            StdOut.println(in.readLine());
            String line = in.readLine();

            String[] elements = line.split(" ");
            String name = elements[0];
            int i1 = Integer.valueOf(elements[1]);
            int i2 = Integer.valueOf(elements[2]);
            double result = 1.0 * i1 / i2;
            StdOut.printf("%-20s\t%3d\t%3d\t%.3f\n", name, i1, i2, result);
        }
    }

}
