package com.youngzy.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 同 GenerateParentheses
 *
 * 研究一下官方解
 */
public class GenerateParentheses2 {

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();

        if (n == 0) {
            return ans;
        }

        // 有效串必定以 左括号 开头
        backtrack(ans, "(", n - 1, n, n);

        return ans;
    }

    /**
     * 回溯
     *
     * 自己的：用数组暂存有效串
     * 官解：直接用字符串
     *
     * 官解确实更优。1ms vs 3ms
     *
     * 为什么？没想明白。。
     */
    private void backtrack(List<String> ans, String validParentheses, int openCount, int closeCount, int max) {

        if (max * 2 == validParentheses.length()) {
            ans.add(validParentheses);
            return;
        }

        // 先尝试左括号再尝试右括号
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                // 左括号
                if (openCount > 0) {
                    backtrack(ans, validParentheses + "(", openCount - 1, closeCount, max);
                }
            } else {
                // 右括号
                if (closeCount > 0) {
                    // 现有的左括号数 > 右括号数，才可以新增右括号
                    // 反过来讲，剩余的open < 剩余的close 数量，才可以追加 右括号
                    if (openCount < closeCount) {
                        backtrack(ans, validParentheses + ")", openCount, closeCount - 1, max);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        GenerateParentheses2 gp = new GenerateParentheses2();
        System.out.println(gp.generateParenthesis(3));
    }
}
