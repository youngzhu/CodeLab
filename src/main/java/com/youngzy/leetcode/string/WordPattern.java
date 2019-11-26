package com.youngzy.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 *
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 *
 * 示例1:
 *
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * 示例 2:
 *
 * 输入: pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 *
 * 说明:
 * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。
 *
 * 链接：https://leetcode-cn.com/problems/word-pattern
 */
public class WordPattern {
    public boolean wordPattern(String pattern, String str) {

        // 0 先用长度做简单的判断
        String[] words = str.split(" ");
        if (words.length != pattern.length()) {
            return false;
        }

        // 1 找出pattern中出现的所有字母
        Map<Character, String> patternMap = new HashMap<Character, String>();
        for (char c : pattern.toCharArray()) {
            if (! patternMap.containsKey(c)) {
                patternMap.put(c, "");
            }
        }

        // 2 匹配字母跟单词
        for (Character c : patternMap.keySet()) {
            int idx = pattern.lastIndexOf(c);
            // 多个字母映射同一个单词，返回false
            if (patternMap.containsValue(words[idx])) {
                return false;
            } else {
                patternMap.put(c, words[idx]);
            }
        }

        // 3 将str中的单词用pattern中的字母替换
        String newStr = str + " ";
        for (Map.Entry<Character, String> entry : patternMap.entrySet()) {
            newStr = newStr.replaceAll(entry.getValue() + " ", entry.getKey() + "");
        }

        // 4 替换后的字符串跟 pattern 比较
        return newStr.equals(pattern);

    }

    public static void main(String[] args) {
        WordPattern wp = new WordPattern();
        String pattern = "abc" , str = "b c a";

        wp.wordPattern(pattern, str);
    }
}
