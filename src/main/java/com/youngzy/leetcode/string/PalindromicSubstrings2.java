package com.youngzy.leetcode.string;

/**
 * 动态规划
 */
public class PalindromicSubstrings2 implements PalindromicSubstrings {
    public int countSubstrings(String s) {
        int ans = 0;
        // 比 int 在空间上稍微好点
        // boolean 跟byte 差不多
        byte[][] dp = new byte[s.length()][s.length()]; // 1 表示是回文

        // dp[i][j]
        // 注意这里的i，j
        // 一般嵌套循环，习惯了先是i，再是j
        // 这里要注意i和j的含义：
        // 以j为终点，判断前j个字符有多少个回文串
        // i 则在 0-j之间
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i ++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1] == 1)) {
                    dp[i][j] = 1;
                    ans++;
                }
            }
        }

        return ans;
    }

}
