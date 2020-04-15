package com.youngzy.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 正则表达式匹配
 *
 * 学习优解
 */
public class RegularExpressionMatching3 {

    /**
     * 回溯。
     * 厉害了
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        // 这个判断挺巧妙的
        if (p.isEmpty()) {
            return s.isEmpty();
        }

        // 第一个字符是否匹配
        boolean isFirstMatch = ! s.isEmpty() && // s 不为空
                                    (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');

        if (p.length() >= 2 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2)) // 匹配0次
                    || (isFirstMatch && isMatch(s.substring(1), p)); // 匹配1次
        } else {
            // 挨个继续比较
            return isFirstMatch && isMatch(s.substring(1), p.substring(1));
        }
    }
}
