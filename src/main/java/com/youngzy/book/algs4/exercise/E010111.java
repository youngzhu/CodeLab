package com.youngzy.book.algs4.exercise;

import edu.princeton.cs.algs4.StdOut;

/**
 * 打印出一个二维布尔数组的内容
 * 其中*表示真，空格表示假
 * 打印出行号和列号
 *
 * @author youngzy
 * @since 2021-07-24
 */
public class E010111 {
    public static void main(String[] args) {
        boolean[][] matrix = {
                {true, true, false},
                {false, true, false},
                {false, true, true}
        };

        print(matrix);
    }

    private static void print(boolean[][] matrix) {
        // 打印行
        print(false); // 空一个元素
        for (int j = 0; j < matrix[0].length; j++)
            print(j);
        StdOut.println();
        for (int i = 0; i < matrix.length; i ++) {
            print(i);
            for (int j = 0; j < matrix[0].length; j++) {
                print(matrix[i][j]);
            }
            StdOut.println();
        }
    }

    private static void print(boolean b) {
        if (b)
            StdOut.print(" * ");
        else
            StdOut.print("   ");
    }

    private static void print(int i) {
        StdOut.print(" " + i + " ");
    }

}
