package com.youngzy.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * 例如，给出 n = 3，生成结果为：
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 */
public class GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();

        if (n == 0) {
            return ans;
        }

        String[] validParentheses = new String[n * 2]; // 有效的括号序列
        // 第一个必定是左括号，最后一个必定是右括号
        validParentheses[0] = "(";
        validParentheses[n * 2 - 1] = ")";

        backtrack(ans, validParentheses, 1, n - 1, n - 1);

        return ans;
    }

    /**
     * 回溯
     *
     * @param ans - 最终结果
     * @param validParentheses - 有效的括号序列，如 ["(", "(", ")", ")"]
     * @param idx - validParentheses 的下标
     * @param openCount - 已使用的左括号数量
     * @param closeCount - 已使用的右括号的数量
     */
    private void backtrack(List<String> ans, String[] validParentheses, int idx, int openCount, int closeCount) {

        if (idx == validParentheses.length - 1) {
            ans.add(String.join("", validParentheses));
            return;
        }

        // 先尝试左括号再尝试右括号
        // 左括号
        if (openCount > 0) {
            validParentheses[idx] = "(";
            backtrack(ans, validParentheses, idx + 1, openCount - 1, closeCount);
        }
        // 右括号
        // 现有的左括号数 > 右括号数，才可以新增右括号
        // 反过来讲，剩余的open <= 剩余的close 数量，才可以追加 右括号
        if (openCount <= closeCount) {
            validParentheses[idx] = ")";
            backtrack(ans, validParentheses, idx + 1, openCount, closeCount - 1);
        }
    }

    /**
     * 回溯
     *
     * @param ans - 最终结果
     * @param validParentheses - 有效的括号序列，如 ["(", "(", ")", ")"]
     * @param idx - validParentheses 的下标
     * @param openCount - 已使用的左括号数量
     * @param closeCount - 已使用的右括号的数量
     */
    private void backtrack1(List<String> ans, String[] validParentheses, int idx, int openCount, int closeCount) {

        if (idx == validParentheses.length - 1) {
            ans.add(String.join("", validParentheses));
            return;
        }

        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                // 左括号
                if (openCount > 0) {
                    validParentheses[idx] = "(";
//                    openCount--;
                    backtrack(ans, validParentheses, idx + 1, openCount - 1, closeCount);
                }
            } else {
                // 右括号
                if (closeCount > 0) {
                    // 现有的左括号数 > 右括号数，才可以新增右括号
                    // 反过来讲，剩余的open <= 剩余的close 数量，才可以追加 右括号
                    if (openCount <= closeCount) {
                        validParentheses[idx] = ")";
//                        closeCount--;
                        backtrack(ans, validParentheses, idx + 1, openCount, closeCount - 1);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        GenerateParentheses gp = new GenerateParentheses();
        System.out.println(gp.generateParenthesis(3));
    }
}
