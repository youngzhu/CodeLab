package com.youngzy.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 *
 * 示例：
 *
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 *
 * 说明：
 *
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 *
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring
 */
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        // 用来存储字符串 t 中各字符出现的次数
        Map<Character, Integer> map = new HashMap<>();

        // 将所有字符，即 C 中的字符，次数初始化为 0
        for (char c : s.toCharArray()) {
            map.put(c, 0);
        }

        // 将 t 中的字符串装载到 map 里
        for (char c : t.toCharArray()) {
            if (map.containsKey(c)) {
                // t 中的字符出现次数 + 1
                map.put(c, map.get(c) + 1);
            } else {
                // t 中的字符 s 中不存在，直接返回空串
                return "";
            }
        }

        String minWindow = ""; // 字符窗口
        int left = 0, right = 0; // 窗口的两边
        // 最小窗口的长度
        // 初始化为最大值，方便一步步缩小
        int minWindowLen = Integer.MAX_VALUE;
        // t 中字符的计数器
        // 在s中出现一个t字符就 -1，=0 时代表已成功找到窗口
        int counter = t.length();

        while (right < s.length()) {
            // 扩展窗口的右边界
            char charR = s.charAt(right);
            if (map.get(charR) > 0) {
                counter --;
            }
            // 这一句不能放在if里
            // 后面要根据值是否为0来判断该字符是否是t字符
            // 无差别的减，对应后面无差别的加
            map.put(charR, map.get(charR) - 1);
            right ++;

            // 当存在可行窗口时，尽量缩小
            // 即，移动左边界
            while (counter == 0) {
                // 缩小窗口
                if (minWindowLen > right - left) {
                    minWindowLen = right - left;
                    minWindow = s.substring(left, right);
                }

                char charL = s.charAt(left);
                // =0 则代表排除了一个t字母
                // 需要重新扩大窗口，即移动right
                if (map.get(charL) == 0) {
                    counter ++;
                }
                map.put(charL, map.get(charL) + 1);
                left ++;

            }

        }

        return minWindow;
    }
}
