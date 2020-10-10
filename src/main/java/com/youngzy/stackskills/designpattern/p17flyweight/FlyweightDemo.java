package com.youngzy.stackskills.designpattern.p17flyweight;

public class FlyweightDemo {
    private static final int ROWS = 6, COLS = 10;

    public static void main(String[] args) {

        Factory factory = new Factory(ROWS);

        for (int i = 0; i < ROWS; i ++) {
            for (int j = 0; j < COLS; j++) {
                factory.getFlyweight(i).report(j);
            }
            System.out.println();
        }

    }
}

class FlyweightGazillion {
    private int row;

    public FlyweightGazillion(int row) {
        this.row = row;
        System.out.println("row: " + this.row);
    }

    void report(int col) {
        System.out.print(" " + row + col);
    }
}

class Factory {
    private FlyweightGazillion[] pool;

    public Factory(int maxRows) {
        pool = new FlyweightGazillion[maxRows];
    }

    public FlyweightGazillion getFlyweight(int row) {
        if (pool[row] == null) {
            pool[row] = new FlyweightGazillion(row);
        }
        return pool[row];
    }
}
