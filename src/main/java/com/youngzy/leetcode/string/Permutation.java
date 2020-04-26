package com.youngzy.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 无重复字符串的排列组合。（跟GenerateParentheses很像）
 *
 * 编写一种方法，计算某字符串的所有排列组合，字符串每个字符均不相同。
 *
 * 示例1:
 *  输入：S = "qwe"
 *  输出：["qwe", "qew", "wqe", "weq", "ewq", "eqw"]
 *
 * 示例2:
 *  输入：S = "ab"
 *  输出：["ab", "ba"]
 *
 * 提示:
 *     字符都是英文字母。
 *     字符串长度在[1, 9]之间。
 *
 * 链接：https://leetcode-cn.com/problems/permutation-i-lcci
 */
public class Permutation {
    /**
     * 直接确定数组的大小，不用list转换
     *
     * 结果：没啥变化
     *
     * @param S
     * @return
     */
    public String[] permutation(String S) {

        if (S.length() == 1) {
            return new String[]{S};
        } else {
            String[] result = new String[factorialCalculator(S.length())];
            int idx = 0;
            char lastChar = S.charAt(S.length() - 1);

            for (String str : permutation(S.substring(0, S.length() - 1))) {

                for (int i = 0; i <= str.length(); i++) {
                    if (i == 0) {
                        result[idx ++] = lastChar + str;
                    } else if (i == str.length()) {
                        result[idx ++] = str + lastChar;
                    } else {
                        String left = str.substring(0, i);
                        String right = str.substring(i, str.length());
                        result[idx ++] = left + lastChar + right;
                    }
                }
            }

            return result;
        }

    }

    private int factorialCalculator(int number) {
        if (number == 1) {
            return 1;
        } else {
            return number * factorialCalculator(number - 1);
        }
    }

    public String[] permutation1(String S) {

        if (S.length() == 1) {
            return new String[]{S};
        } else {
            List<String> list = new ArrayList<>();
            char lastChar = S.charAt(S.length() - 1);

            for (String str : permutation(S.substring(0, S.length() - 1))) {

                for (int i = 0; i <= str.length(); i++) {
                    if (i == 0) {
                        list.add(lastChar + str);
                    } else if (i == str.length()) {
                        list.add(str + lastChar);
                    } else {
                        String left = str.substring(0, i);
                        String right = str.substring(i, str.length());
                        list.add(left + lastChar + right);
                    }
                }
            }

            return list.toArray(new String[]{});
        }

    }
}
