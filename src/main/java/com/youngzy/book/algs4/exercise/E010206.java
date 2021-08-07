package com.youngzy.book.algs4.exercise;

import edu.princeton.cs.algs4.StdOut;

/**
 * 如果字符串s中的字符循环移动任意位置之后能够得到另一个字符串t，
 * 那么s就被成为t的回环变位（circular rotation）。
 * 例如，ACTGACG就是TGACGAC的一个回环变位，反之亦然。
 * 判定这个条件在基因组序列的研究中是很重要的。
 *
 * 编写一个程序检查两个给定的字符串s和t是否互为回环变位。
 *
 * 提示：只需要一行用到 indexOf(), length()和字符串连接的代码
 *
 * @author youngzy
 * @since 2021-07-24
 */
public class E010206 {
    static boolean isCircularRotation(String s, String t) {
        return false;
    }
    public static void main(String[] args) {
        StdOut.println(isCircularRotation("ACTGACG", "TGACGAC"));
    }

}
