package com.youngzy.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 正则表达式匹配
 * <p>
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * <p>
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * <p>
 * 说明:
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * <p>
 * 示例 1:
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * <p>
 * 示例 2:
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * <p>
 * 示例 3:
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * <p>
 * 示例 4:
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * <p>
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 */
public class RegularExpressionMatching2 {
    /**
     * 经过前面的失败，准备换条路：对*的匹配使用回溯
     * 因为*可以匹配0次或N次，需要不断试错
     *
     * 计划2h，结果6h+才测试通过。。
     * 效率也不高
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        int idxStar = p.indexOf('*');

        // 回溯
        String matchStr = "";
        List<String> list = new ArrayList<String>(1);
        backtrack(s, p, 0, 0, idxStar, matchStr, list);

        return list.size() > 0;
    }

    /**
     * 回溯
     * 对 * 的匹配从0开始逐渐试错
     *
     * @param s
     * @param p
     * @param sIdx - s 开始匹配的位置
     * @param pIdx - p 开始匹配的位置
     * @param idxStar - * 最新的位置
     * @param matchStr - 已匹配的字符串
     * @param list - 完全匹配的字符串集合。有一个元素就够了
     */
    private void backtrack(String s, String p, int sIdx, int pIdx, int idxStar, String matchStr, List<String> list) {

        // 完全匹配的字符串集合。有一个元素就够了
        if (list.size() > 0) {
            return;
        }

        if (idxStar == -1) {
            // 已经接近或到达末尾

            boolean match = false;
            // 大致2种情形
            // 1, s 和 p 同时到达末尾，match
            // 2, 没有结束继续比较
            if (sIdx == s.length() && pIdx == p.length()) {
                // 1
                match = true;
            } else {
                // 2
                // 不会含有 *
                match = compareWithoutStar(s.substring(sIdx), p.substring(pIdx));
            }

            if (match) {
                list.add(matchStr);
                return;
            }
        } else {
            int len = idxStar - pIdx - 1; // * 及前面一个字符不算，即不参与比较

            if (len > 0) {
                // s 结束，p 还未结束
                if (sIdx + len > s.length()) {
                    return;
                }

                String sStr = s.substring(sIdx, sIdx + len); // s 的比较片段
                if (compareWithoutStar(sStr, p.substring(pIdx, pIdx + len))) {
                    // 匹配则继续
                    matchStr += sStr;
                    sIdx += len;
                } else {
                    // 普通字符都不等，
                    return;
                }
            }

            // 开始对 * 进行匹配
            char beforeStar = p.charAt(idxStar - 1); // * 前面的字符
            pIdx = idxStar + 1;
            idxStar = p.indexOf('*', pIdx);

            // 匹配次数
            int matchTimes = 0;

            while (list.isEmpty() && sIdx + matchTimes <= s.length()) {
                if (matchTimes > 0 && s.charAt(sIdx + matchTimes - 1) != beforeStar && beforeStar != '.') {
                    return;
                }

                if (matchTimes > 0) {
                    matchStr += s.charAt(sIdx + matchTimes - 1);
                }

                backtrack(s, p, sIdx + matchTimes, pIdx, idxStar, matchStr, list);

                matchTimes ++;
            }
        }
    }

    /**
     * 比较 s2 不含 * 的字符串
     *
     * @param s1
     * @param s2
     */
    private boolean compareWithoutStar(String s1, String s2) {
        if (s1.length() != s2.length()) {
            // 长度不等，一定不匹配
            return false;
        }

        if (s2.indexOf('.') == -1) {
            // 不含匹配符，直接比较字符串
            return s1.equals(s2);
        } else {
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) != s2.charAt(i) && s2.charAt(i) != '.') {
                    return false;
                }
            }
        }

        return true;
    }

}
