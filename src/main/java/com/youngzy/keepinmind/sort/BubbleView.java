package com.youngzy.keepinmind.sort;

import org.omg.PortableServer.LIFESPAN_POLICY_ID;

/**
 * 冒泡视图化（桶）
 */
public class BubbleView {
    static String LINE = "*******";
    static String SPACE = "        ";
    static int WIDTH = 4;
    public static void main(String[] args) {

        for (int i = 0; i < WIDTH; i ++) {
            printLine();
            if (i == 0)
                printBorder0();
            else if (i == WIDTH - 1)
                printBorderN();
            else
                printBorder();
        }
        printLine();
    }

    static void printLine() {
        System.out.printf("     %-4.4s\n", LINE);
    }

    static void printBorder() {
        System.out.printf("     *%-2.2s*\n", SPACE);
    }
    static void printBorder0() {
        System.out.printf("a[0] *%-2.2s*\n", SPACE);
    }
    static void printBorderN() {
        System.out.printf("a[n] *%-2.2s*\n", SPACE);
    }
}
