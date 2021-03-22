package com.youngzy.leetcode.string;

/**
 * 基本思路：递归
 *
 * 效率这么差吗，垫底的5% 。。。
 */
public class PalindromicSubstrings1 implements PalindromicSubstrings {
    public int countSubstrings(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        int count = 0;

        //
        char[] chars = s.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            if (isPalindromic(chars, i)) {
                count ++;
            }
        }


        return 1 + count + countSubstrings(new String(chars, 1, chars.length - 1));
    }

    private boolean isPalindromic(char[] chars, int lastIdx) {
        for (int i = 0; i < (lastIdx+1) / 2; i++) {
            if (chars[i] != chars[lastIdx - i])
                return false;
        }

        return true;
    }
}
