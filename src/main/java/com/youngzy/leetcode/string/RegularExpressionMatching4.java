package com.youngzy.leetcode.string;

/**
 * 正则表达式匹配
 *
 * 学习优解
 */
public class RegularExpressionMatching4 {

    /**
     * 动态规划
     *
     * 在回溯的基础上，记住之前的比较结果
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {

        // 判断临界值，所以长度要 length+1
        int[][] ans = new int[s.length() + 1][p.length() + 1];

        return dp(ans, 0, 0, s, p);
    }

    /**
     *
     * 不用match，直接return，相当于回溯，耗时 50 ms左右
     * 用了match，使用动态规划，耗时 2 ms
     *
     * @param ans - 默认值都是 0
     *          设定1-匹配；2-不匹配
     * @param sIdx
     * @param pIdx
     * @param s
     * @param p
     * @return
     */
    private boolean dp(int[][] ans, int sIdx, int pIdx, String s, String p) {
        if (ans[sIdx][pIdx] > 0) {
            // 说明已比较过，已赋过值
            return ans[sIdx][pIdx] == 1;
        }

        boolean match;

        // 用之前的回溯
        // 这个判断挺巧妙的
        // p 结束了，判断s是否结束
        if (pIdx == p.length()) {
            match = sIdx == s.length();
        } else {
            // 第一个字符是否匹配
            boolean isFirstMatch = sIdx < s.length() && // s 不为空
                    (s.charAt(sIdx) == p.charAt(pIdx) || p.charAt(pIdx) == '.');

            if (p.length() >= 2 + pIdx && p.charAt(pIdx + 1) == '*') {
                match = dp(ans, sIdx, pIdx + 2, s, p) // 匹配0次
                        || (isFirstMatch && dp(ans, sIdx + 1, pIdx, s, p));// 匹配1次
            } else {
                // 挨个继续比较
                match = isFirstMatch && dp(ans, sIdx + 1, pIdx + 1, s, p);
            }
        }

        ans[sIdx][pIdx] = match ? 1 : 2;
        return match;
    }
}
