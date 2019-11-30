package com.youngzy.leetcode.string;

import java.util.Arrays;
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

    /**
     * 从 1 copy，不用replace，其余不变
     *
     * 比 3 差，
     *
     * 执行用时 :2 ms , 在所有 java 提交中击败了56.78% 的用户
     * 内存消耗 : 34.1 MB , 在所有 java 提交中击败了 37.37% 的用户
     *
     *
     * @param pattern
     * @param str
     * @return
     */
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

        for (Map.Entry<Character, String> entry : patternMap.entrySet()) {
            int index = pattern.indexOf(entry.getKey());
            int lastIdx = pattern.lastIndexOf(entry.getKey());

            int lastCounter = 0;

            while (index <= lastIdx) {
                if (lastCounter > 0) {
                    // lastCounter > 0 表示该字母最后一次出现的位置也验证过了
                    break;
                }

                if (! words[index].equals(entry.getValue())) {
                    return  false;
                }

                if (index == lastIdx) {
                    lastCounter ++;
                }

                int fromIdx = Math.min(index + 1, lastIdx);
                index = pattern.indexOf(entry.getKey(), fromIdx);
            }

        }

        return true;
    }

    /**
     * 执行用时 :1 ms , 在所有 java 提交中击败了 100.00% 的用户
     * 内存消耗 :34.2 MB , 在所有 java 提交中击败了 36.73% 的用户
     *
     * 效果还不错
     *
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPattern3(String pattern, String str) {
        // 0 先用长度做简单的判断
        String[] words = str.split(" ");
        if (words.length != pattern.length()) {
            return false;
        } else {
            // 充分利用 "pattern 只包含小写字母"的条件
            String[] alphaArr = new String[26];

            String matchedWords = ""; // 已匹配过的单词

            for (char c = 'a'; c <= 'z'; c++) {
                int idx = pattern.indexOf(c);
                if (-1 != idx) {
//                    if (-1 != matchedWords.indexOf(words[idx])) {
//                        // 一个单词映射多个字母
//                        return  false;
//                    } else {
//                        matchedWords += words[idx];
//                    }

                    if (Arrays.asList(alphaArr).contains(words[idx])) {
                        return false;
                    }

                    alphaArr[c - 'a'] = words[idx];
                }
            }

            for (char c = 'a'; c <= 'z'; c++) {
                if (null != alphaArr[c - 'a']) {
                    int index = pattern.indexOf(c);
                    int lastIdx = pattern.lastIndexOf(c);

                    boolean isLast = false; // 是否已比对到该字母最后出现的位置

                    // 将字母出现的位置，逐一和单词进行比较
                    while (index <= lastIdx) {
                        if (isLast) {
                            break;
                        }

                        if (! words[index].equals(alphaArr[c - 'a'])) {
                            return  false;
                        }

                        if (index == lastIdx) {
                            isLast = true;
                        }

                        int fromIdx = Math.min(index + 1, lastIdx);
                        index = pattern.indexOf(c, fromIdx);
                    }

                }
            }
            return true;
        }
    }

    /**
     * 跟 1 居然没变化
     * 看来replace消耗甚大
     *
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPattern2(String pattern, String str) {

        // 0 先用长度做简单的判断
        String[] words = str.split(" ");
        if (words.length != pattern.length()) {
            return false;
        } else {
            String[] alphaArr = new String[26];

            for (char c = 'a'; c <= 'z'; c++) {
                int idx = pattern.indexOf(c);
                if (-1 != idx) {
                    alphaArr[c - 'a'] = words[idx];
                }
            }

            // 3 将str中的单词用pattern中的字母替换
            String newStr = str + " ";
            for (char c = 'a'; c <= 'z'; c++) {
                if (null != alphaArr[c - 'a']) {
                    newStr = newStr.replaceAll(alphaArr[c - 'a'] + " ", c + "");
                }
            }

            // 4 替换后的字符串跟 pattern 比较
            return newStr.equals(pattern);
        }
    }

    public boolean wordPattern1(String pattern, String str) {

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

        pattern = "abba";
        str = "dog cat cat dog";

//        pattern = "aba";
//        str = "dog cat cat";
//
//        pattern = "abba";
//        str = "dog cat cat fish";

        pattern = "aaaa";
        str = "dog cat cat dog";

        wp.wordPattern(pattern, str);
    }
}
