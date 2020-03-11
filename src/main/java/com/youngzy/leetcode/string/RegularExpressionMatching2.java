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
     * <p>
     * 对 * 的匹配从0开始逐渐试错
     *
     * @param s
     * @param p
     * @param sIdx    - s 开始的位置
     * @param pIdx    - p 开始的位置
     * @param idxStar
     * @param matchStr
     */
    private void backtrack(String s, String p, int sIdx, int pIdx, int idxStar, String matchStr, List<String> list) {

        if (list.size() > 0) {
            return;
        }

        if (idxStar == -1) {

            // s 和 p 同时到达末尾
            boolean case3 = sIdx == s.length() && pIdx == p.length();

            if (case3) {
                list.add(matchStr);
                return;
            }

            // 剩余的字符都一样
            boolean case1 = compareWithoutStar(s.substring(sIdx), p.substring(pIdx));
            // 以 * 结尾的
            boolean case2 =  false;

            // p 长度至少是 2
            if (p.length() >= 2 &&p.charAt(p.length() - 1) == '*' && compareWithoutStar("" + s.charAt(sIdx), "" + p.charAt(p.length() - 2))) {

                int loop = s.length() - sIdx;
                String sTmp = "";
                while (loop > 0) {
                    sTmp += s.charAt(sIdx);
                    loop --;
                }

                case2 = sTmp.equals(s.substring(sIdx));
            }

            if (case1 || case2) {
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

            int pIdxNew = idxStar + 1;
            int idxStarNew = p.indexOf('*', pIdxNew);

            // 匹配0次
            backtrack(s, p, sIdx, pIdxNew, idxStarNew, matchStr, list);

            if (list.isEmpty() && sIdx < s.length()) {

                // 匹配大于 0 次
                int matchTimes = 1;
                while (matchTimes <= s.length() - sIdx) {

                    if (s.charAt(sIdx + matchTimes - 1) != p.charAt(idxStar - 1) && p.charAt(idxStar - 1) != '.') {
                        return;
                    }

                    matchStr += s.charAt(sIdx + matchTimes - 1);

                    backtrack(s, p, sIdx + matchTimes, pIdxNew, idxStarNew, matchStr, list);

                    matchTimes ++;
                }
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
