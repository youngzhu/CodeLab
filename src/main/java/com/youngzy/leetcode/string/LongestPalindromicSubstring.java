package com.youngzy.leetcode.string;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 回文的意思是正着念和倒着念一样
 *
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 *
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 */
public class LongestPalindromicSubstring {

    /**
     * 动态规划法
     * 效率没中心扩展算法高
     *
     * 将结果存储起来，避免重复计算
     * 以空间换时间
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {

        if (s == null || s.length() <= 1) {
            return s;
        }

        int maxLen = 1; // 回文长度至少是1
        int maxStart = 0;
        // 1 表示是回文，0 - 否
        // byte 比 int 省时间+空间
        byte[][] dp = new byte[s.length()][s.length()];

        for (int right = 1; right < s.length(); right++) {
            // 找到right左边最长的回文
            for (int left = 0; left < right; left++) {
                if (s.charAt(right) == s.charAt(left)
                    && (dp[right - 1][left + 1] == 1 || right - left <= 2)) {
                    dp[right][left] = 1; // 是回文

                    int len = right - left + 1;
                    if (len > maxLen) {
                        maxLen = len;
                        maxStart = left;
                    }

                }

            }
        }

        return s.substring(maxStart, maxStart + maxLen);
    }

    /**
     * 中心扩散法（优化版）
     *
     * @param s
     * @return
     */
    public String longestPalindrome2(String s) {

        if (s == null || s.length() <=1) {
            return s;
        }

        int maxLen = 0, maxStart = 0;

        for (int center = 0; center < s.length(); center ++) {
            /*
            有两类中心
            一类奇数的，如 aba，b是中心
            一类是偶数的，如abba，bb是中心
             */
            int len1 = expendAroundCenter(s, center, center);
            int len2 = expendAroundCenter(s, center, center + 1);
            int len = Math.max(len1, len2);

            if (len > maxLen) {
                maxLen = len;
                maxStart = center - (len - 1) / 2;
            }
        }

        return s.substring(maxStart, maxStart + maxLen);
    }

    private int expendAroundCenter(String s, int center1, int center2) {
        int left = center1, right = center2; // 从中心自身算起

        while (left >= 0
                && right < s.length()
                && s.charAt(right) == s.charAt(left)) {
            left --;
            right ++;
        }

        // right - left - 2 + 1
        return right - left - 1;
    }

    /**
     * 中心扩散法
     *
     * @param s
     * @return
     */
    public String longestPalindrome1(String s) {

        if (s == null || s.length() <=1 ) {
            return s;
        }

        int left, right;
        int len;
        int maxLen = 0, maxStart = 0;

        for (int cur = 1; cur < s.length(); cur ++) {
            len = 1;
            left = cur - 1;
            right = cur + 1;

            // 左扩散
            while (left >= 0 && s.charAt(left) == s.charAt(cur)) {
                len ++;
                left --;
            }

            // 右扩散
            while (right < s.length() && s.charAt(right) == s.charAt(cur)) {
                len ++;
                right ++;
            }

            // 两边扩散
            while (left >= 0
                    && right < s.length()
                    && s.charAt(right) == s.charAt(left)) {
                len += 2;
                left --;
                right ++;
            }

            if (len > maxLen) {
                maxLen = len;
                maxStart = left;
            }
        }

        return s.substring(maxStart + 1, maxStart + 1 + maxLen);
    }
}
