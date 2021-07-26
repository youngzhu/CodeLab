package com.youngzy.book.algs4.exercise;

import edu.princeton.cs.algs4.StdOut;

/**
 * 打印出一个M行N列的二维数组的转置（交换行和列）
 *
 * @author youngzy
 * @since 2021-07-24
 */
public class E010113 {
    public static void main(String[] args) {

        int[][] matrix = {
                {1, 2},
                {3, 4},
                {5, 6}
        };

        StdOut.println("Before:");
        print(matrix);

        int[][] result = transpose(matrix);

        StdOut.println("After:");
        print(result);
    }

    private static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++)
                StdOut.print(matrix[i][j] + " ");
            StdOut.println();
        }
    }

    private static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];

        for (int m = 0; m < result.length; m++)
            for (int n = 0; n < result[0].length; n++)
                result[m][n] = matrix[n][m];

        return result;
    }
}
