package com.youngzy.leetcode.string;

/**
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 * 案例:
 *
 * s = "leetcode"
 * 返回 0.
 *
 * s = "loveleetcode",
 * 返回 2.
 *  
 *
 * 注意事项：您可以假定该字符串只包含小写字母。
 *
 * 链接：https://leetcode-cn.com/problems/first-unique-character-in-a-string
 */
public class FirstUniqueChar {

    /**
     * 1 ms	38.1 MB
     *
     * 跟 2 相比，仅循环了 26 次。不用对字符串中的每个字母去遍历
     *
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        int idx = -1;

        for (char c = 'a'; c <= 'z'; c++) {
            int firstIdx = s.indexOf(c);

            if (firstIdx != -1
                    && firstIdx == s.lastIndexOf(c)) {
                if (idx == -1 || idx > firstIdx) {
                    idx = firstIdx;
                }
            }
        }

        return idx;
    }

    /**
     * 借鉴
     *
     * 5 ms	37.7 MB
     *
     * @param s
     * @return
     */
    public int firstUniqChar3(String s) {
        int[] arr = new int[26];

        for (char c : s.toCharArray()) {
            arr[c - 'a'] ++;
        }

        for (char c : s.toCharArray()) {
            if (arr[c - 'a'] == 1) {
                return s.indexOf(c);
            }
        }

        return -1;
    }


    /**
     * 借鉴
     *
     * 比 1 快了10倍
     *
     * 21 ms	38 MB
     *
     * @param s
     * @return
     */
    public int firstUniqChar2(String s) {

        for (Character c : s.toCharArray()) {

            if (s.indexOf(c) == s.lastIndexOf(c)) {
                return s.indexOf(c);
            }
        }

        return -1;
    }

    public int firstUniqChar1(String s) {
        int idx = -1;

        String tmp = s;
        for (Character c : s.toCharArray()) {
            int length = tmp.length();
            tmp = tmp.replaceAll(c + "", "");
            int newLen = tmp.length();

            if (length - newLen == 1) {
                return s.indexOf(c);
            }
        }

        return idx;
    }
}
