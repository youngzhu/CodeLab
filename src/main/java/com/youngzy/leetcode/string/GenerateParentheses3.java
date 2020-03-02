package com.youngzy.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 同 GenerateParentheses
 *
 * 研究一下官方解
 */
public class GenerateParentheses3 {

    /**
     * 直接用String，不用StringBuilder试试
     * 结果：没啥差别
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();

        if (n == 0) {
            ans.add("");
            return ans;
        }

        String tmp;
        for (String str : generateParenthesis(n - 1)) {
            // 直接在原串后面加  "()"
            tmp = str + "()";
            if (!ans.contains(tmp)) {
                ans.add(tmp);
            }

            // 在每个")"前插入"()"
            int i = str.indexOf(")", 1);
            while (i > 0) {

                tmp = str.substring(0, i);
                tmp += "()";
                tmp += str.substring(i);

                if (!ans.contains(tmp)) {
                    ans.add(tmp);
                }

                i = str.indexOf(")", i + 1);
            }
        }

        return ans;
    }

    public List<String> generateParenthesis1(int n) {
        List<String> ans = new ArrayList<>();

        if (n == 0) {
            ans.add("");
            return ans;
        }

        StringBuilder sb;
        for (String str : generateParenthesis(n - 1)) {
            sb = new StringBuilder(str);
            sb.append("()");
            if (!ans.contains(sb.toString())) {
                ans.add(sb.toString());
            }

            // 在每个")"前插入"()"
            int i = str.indexOf(")", 1);
            while (i > 0) {

                sb = new StringBuilder(str);
                sb.insert(i, "()");

                if (!ans.contains(sb.toString())) {
                    ans.add(sb.toString());
                }

                i = str.indexOf(")", i + 1);
            }
        }

        return ans;
    }

}
