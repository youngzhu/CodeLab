package com.youngzy.leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 示例:
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 *
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 *
 */
public class LetterCombinationsPhoneNumber {
    public List<String> letterCombinations(String digits) {
        List<String>  ans = new ArrayList<>();

        if (digits.length() < 1) {
            return ans;
        }

        // 一次按键只有一个字母
        // 所以 digits的长度 = 最终字母组合的长度
        String[] letterArr = new String[digits.length()];
        backtrack(ans, digits, 0, letterArr);

        return ans;
    }

    private static final String[] LETTER_DIGIT_ARR = {
            "",     //0
            "",     //1
            "abc",  //2
            "def",  //3
            "ghi",  //4
            "jkl",  //5
            "mno",  //6
            "pqrs", //7
            "tuv",  //8
            "wxyz"  //9
    };

    /**
     * 回溯（用数组）
     *
     * @param ans
     * @param digits
     * @param idx
     * @param letterArr
     */
    private void backtrack(List<String> ans, String digits, int idx, String[] letterArr) {
        // 注意不能用 valueOf，因为没有 char 做参数的
        int index = digits.charAt(idx) - '0';
        char[] arr = LETTER_DIGIT_ARR[index].toCharArray();

        for (int i = 0; i < arr.length; i++) {
            letterArr[idx] = String.valueOf(arr[i]);

            if (idx == digits.length() - 1) {
                // 结束
                ans.add(String.join("", letterArr));
            } else {
                //
                backtrack(ans, digits, idx + 1, letterArr);
            }
        }
    }

    private static final Map<Character, String[]> LETTER_DIGIT_MAP = new HashMap<Character, String[]>() {
        {
            put('2', new String[]{"a", "b", "c"});
            put('3', new String[]{"d", "e", "f"});
            put('4', new String[]{"g", "h", "i"});
            put('5', new String[]{"j", "k", "l"});
            put('6', new String[]{"m", "n", "o"});
            put('7', new String[]{"p", "q", "r", "s"});
            put('8', new String[]{"t", "u", "v"});
            put('9', new String[]{"w", "x", "y", "z"});
        }

    };

    /**
     * 回溯（用Map）
     *
     * @param ans
     * @param digits
     * @param idx
     * @param letterArr
     */
    private void backtrack1(List<String> ans, String digits, int idx, String[] letterArr) {
        String[] arr = LETTER_DIGIT_MAP.get(digits.charAt(idx));
        for (int i = 0; i < arr.length; i++) {
            letterArr[idx] = arr[i];

            if (idx == digits.length() - 1) {
                // 结束
                ans.add(String.join("", letterArr));
            } else {
                //
                backtrack(ans, digits, idx + 1, letterArr);
            }
        }
    }
}
