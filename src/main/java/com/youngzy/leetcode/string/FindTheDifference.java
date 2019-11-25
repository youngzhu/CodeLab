package com.youngzy.leetcode.string;


/**
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 *
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 *
 * 请找出在 t 中被添加的字母。
 *
 *
 * 示例:
 *
 * 输入：
 * s = "abcd"
 * t = "abcde"
 *
 * 输出：
 * e
 *
 * 解释：
 * 'e' 是那个被添加的字母。
 *
 * 链接：https://leetcode-cn.com/problems/find-the-difference
 */
public class FindTheDifference {

    public char findTheDifference(String s, String t) {

        for (int i = 0; i < s.length(); i ++) {
             t = t.replaceFirst("" + s.charAt(i), "");
        }

        return t.charAt(0);

    }

    /**
     * 两组char之和相减，差值就是那个新增的字母
     *
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference2(String s, String t) {

        int sum1 = 0, sum2 = 0;

        for (char tt : t.toCharArray()) {
            sum1 += tt;
        }

        for (char ss : s.toCharArray()) {
            sum2 += ss;
        }

        return (char) (sum1 - sum2);
    }

    /**
     * 异或，看答案的。自己完全没想法。。。
     *
     * 原理：对于任何数x，都有x^x=0，x^0=x
     *
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference1(String s, String t) {
        char ch = 0;

        for (char tt : t.toCharArray()) {
            ch ^= tt;
        }

        for (char ss : s.toCharArray()) {
            ch ^= ss;
        }

        return ch;
    }
}
