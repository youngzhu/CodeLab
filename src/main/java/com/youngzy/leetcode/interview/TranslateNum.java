package com.youngzy.leetcode.interview;

import java.util.HashMap;
import java.util.Map;

/**
 * 面试题46. 把数字翻译成字符串
 *
 * 给定一个数字，我们按照如下规则把它翻译为字符串：
 * 0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
 * 一个数字可能有多个翻译。
 * 请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 *
 * 示例 1:
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 *
 * 链接：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
 */
public class TranslateNum {
    static final Map<String, Integer> NUMBER_MAP = new HashMap<>();
    static {
        for (char c = 'a'; c <= 'z'; c++) {
            NUMBER_MAP.put(String.valueOf(c - 'a'), 1);
        }
    }

    public int translateNum(int num) {
        return dp(String.valueOf(num), String.valueOf(num));
    }

    private int dp(String originalNum, String sub) {
        if (sub.length() <= 1) {
            return 1;
        }

        int result = dp(originalNum, sub.substring(1));
        if (NUMBER_MAP.get(sub.substring(0, 2)) != null) {
            result += dp(originalNum, sub.substring(2));
        }

        return result;
    }
}
