package com.youngzy.stackskills.designpattern.p17flyweight;

public class Demo {
    private static final int ROWS = 6, COLS = 10;

    public static void main(String[] args) {
        Gazillion[][] matrix = new Gazillion[ROWS][COLS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                matrix[i][j] = new Gazillion(COLS);
            }
        }

        for (int i = 0; i < ROWS; i ++) {
            for (int j = 0; j < COLS; j++) {
                matrix[i][j].report();
            }
            System.out.println();
        }

    }
}

class Gazillion {
    private static int num = 0;

    private int row, col;

    public Gazillion(int maxPerRow) {
        row = num / maxPerRow;
        col = num % maxPerRow;
        num++;
    }

    void report() {
        System.out.print(" " + row + col);
    }
}
