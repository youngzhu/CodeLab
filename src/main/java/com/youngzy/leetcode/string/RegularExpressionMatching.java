package com.youngzy.leetcode.string;

/**
 * 正则表达式匹配
 *
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 *
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *
 * 说明:
 *     s 可能为空，且只包含从 a-z 的小写字母。
 *     p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 *
 * 示例 1:
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 *
 * 示例 2:
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 *
 * 示例 3:
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 *
 * 示例 4:
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 *
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 */
public class RegularExpressionMatching {
    /**
     * 花了6个小时，写了这些。
     *
     * 失败了N次，也尝试了N次，最终败在 "ab"，".*.." 上。
     *
     * 不想再试了。换条路
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {

        if (".*".equals(p)) {
            return true;
        }

        // 先找出特殊字符
        int idxDot = p.indexOf('.') < 0 ? Integer.MAX_VALUE : p.indexOf('.');
        int idxStar = p.indexOf('*') < 0 ? Integer.MAX_VALUE : p.indexOf('*');
        int idx = Math.min(idxDot, idxStar);

        // 没有通配符
        if (idx == Integer.MAX_VALUE) {
            return s.equals(p);
        }

        int lastIdx = 0; // 上一轮的idx
        int sIdx = lastIdx; // s 再次比较的起始位置
        char beforeStar = ' '; // * 前面的字符
        do {

            if (idx > 0) idx --; // 不包括特殊字符前一个字符
            if (idx > lastIdx) {
                // 比较非特殊字符段
                // 截止到（不包括）特殊字符前一个字母
                if (! s.substring(sIdx, sIdx + idx - lastIdx).equals(p.substring(lastIdx, idx))) {
                    return false;
                }
            }

            sIdx = sIdx + idx - lastIdx;
            lastIdx = idx;


            // 以下比较至少2个字符
            if (p.length() - idx < 2) {
                break;
            }
            // 处理特殊字符
            switch (p.charAt(idx + 1)) {
                case '.' :
                    // dot
                    if (sIdx == s.length()) {
                        // s 已经结束，p还未结束
                        return false;
                    }
                    char before = p.charAt(idx); // 前面的字符
                    if (before == '*' || before == '.') {
                        // "*."
                        sIdx = sIdx + 1;
                        idx = idx + 1;

                    } else {
                        // 如果是字母则比较字母是否相等
                        if (s.charAt(sIdx) != before) {
                            return false;
                        }

                        sIdx = sIdx + 2;
                        idx = idx + 2;
                    }

                    break;
                case '*' :
                    // star
                    char ch = p.charAt(idx); // * 前面的字符

                    int sIdxBefore = sIdx;
                    while (sIdx < s.length()
                            && (ch == s.charAt(sIdx) || ch == '.')) {
                        beforeStar = ch;
                        sIdx ++;
                    }

                    // 不管*匹配0次还是多次，都+2
                    idx = idx + 2;
                    break;
                default:
            }

            lastIdx = idx;
            // 继续循环
            idxDot = p.indexOf('.', idx) < 0 ? Integer.MAX_VALUE : p.indexOf('.', idx);
            idxStar = p.indexOf('*', idx) < 0 ? Integer.MAX_VALUE : p.indexOf('*', idx);
            idx = Math.min(idxDot, idxStar);
        } while (idx < Integer.MAX_VALUE && idx > lastIdx);

        // 特殊字符都比完了，还剩字符
        // case 3
        if (sIdx < s.length() && lastIdx < p.length()) {
            // 都没结束，继续比较
            //
            if (sIdx == s.length() -1 && lastIdx == p.length() - 1) {
                // 就剩一个
                return s.charAt(sIdx) == p.charAt(lastIdx) || p.charAt(lastIdx) == '.';
            } else {
                // 剩多个
                return s.substring(sIdx, s.length()).equals(p.substring(lastIdx, p.length()));
            }
        }

        /*
        aaa
        a*a
         */
        /*
        ab
        .*c
         */
        if (lastIdx == p.length() - 1) {
            // 如果p还剩最后一个没比较
            // 要么跟* 匹配过，可以吐出一个来
            boolean case1 = s.charAt(s.length() - 1) == p.charAt(lastIdx)
                    && (s.charAt(s.length() - 1) == beforeStar
                        // bbbba , .*a*a
                        || beforeStar == '.');
            // 要么是 . 匹配任意字符
            boolean case2 = p.charAt(lastIdx) == '.';
            return case1 || case2 ;
        }


        // 跟p匹配完，如果到达了s的末尾，则成功
        // 否则失败
        return sIdx == s.length() && lastIdx == p.length();
    }

}
