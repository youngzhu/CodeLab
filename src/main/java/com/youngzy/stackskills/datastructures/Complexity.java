package com.youngzy.stackskills.datastructures;

/**
 * 计算复杂度时，都假设n足够大
 */
public class Complexity {
    /**
     * O(N)
     *
     * O(N + 100)，100可忽略
     *
     * @param n
     */
    public void foo1(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("Printing: " + i);
        }

        for (int i = 0; i < 100; i++) {
            System.out.println("Printing: " + i);
        }
    }

    /**
     * O(N + M)
     *
     * @param n
     */
    public void foo2(int n, int m) {
        for (int i = 0; i < n; i++) {
            System.out.println("Printing: " + i);
        }

        for (int i = 0; i < m; i++) {
            System.out.println("Printing: " + i);
        }
    }

    /**
     * O(N * M)
     *
     * @param n
     */
    public void foo3(int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.println("Printing: " + (i * j));
            }
        }
    }

    /**
     * O(N^2)
     *
     * O(N^2 + N)
     *
     * @param n
     */
    public void foo4(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println("Printing: " + (i * j));
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.println("Printing: " + i);
        }
    }

    /**
     * O(N^2)
     *
     * O(N * (N / 2))
     *
     * @param n
     */
    public void foo5(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                System.out.println("Printing: " + (i * j));
            }
        }
    }

    /**
     * O(logN)
     *
     * 时间复杂度可以看成是循环的次数
     *
     * n = i = i * 2
     * 循环1次：n = 1 * 2 = 2^1
     * 2: n = 2 * 2 = 2^2
     * 3: n = 4 * 2 = 2^3
     * ...
     * k: n = 2^(k-1) * 2 = 2^k
     *
     * k = log N
     *
     * @param n
     */
    public void foo6(int n) {
        for (int i = 0; i < n;) {
            System.out.println("Printing: " + i);
            i = i * 2;
        }
    }

    /**
     * O(logN)
     *
     * @param n
     */
    public void foo7(int n) {
        for (int i = n; i > 0;) {
            System.out.println("Printing: " + i);
            i = i / 2;
        }
    }

}
