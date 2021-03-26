package com.youngzy.leetcode.string;

/**
 * 学习：中心扩展法
 * 时间：方法2 的1/3
 *
 * 选定一个中心点，向两边扩散
 * x[c]y
 * 如果c是回文，且x=y，则x[c]y是回文，继续向左右扩展
 * 如果不是，则寻找下一个中心点
 *
 * 为什么需要两类中心点？
 * 扩展的字符串长度一定是 中心点的长度 + 2 * n，n>=0
 * 如果中心点只有一位，那么扩展的字符串长度永远是基数
 * 所以还需要两位的中心点，这样，扩展的字符串长度才能覆盖全面
 *
 * 也可以看看这个：
 * https://leetcode-cn.com/problems/palindromic-substrings/solution/liang-dao-hui-wen-zi-chuan-de-jie-fa-xiang-jie-zho/
 */
public class PalindromicSubstrings3 implements PalindromicSubstrings {
    public int countSubstrings(String s) {
        int ans = 0;

        // 两种算法可以合并一下
        // 但分开跟容易理解

        // 一个字符的中心点
        for (int center = 0 ; center < s.length(); center++) {
            int left = center;
            int right = center;
            while (left>=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                ans++;
                left--;
                right++;
            }
        }

        // 两个字符的中心点
        for (int center = 0 ; center < s.length(); center ++) {
            int left = center;
            int right = center + 1;
            while (left>=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                ans++;
                left--;
                right++;
            }
        }

        return ans;
    }

}
