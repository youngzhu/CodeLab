package com.youngzy.leetcode.basicalgorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 *
 * 输入: "()"
 * 输出: true
 * 示例 2:
 *
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 *
 * 输入: "(]"
 * 输出: false
 *
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 */
public class ValidParentheses {
    private static final Map<Character, Character> PARENTHESES_MAP = new HashMap<Character, Character>() {
        {

            put(')', '(');
            put(']', '[');
            put('}', '{');
        }
    };

    private Map<Character, Character> map;

    public ValidParentheses() {
        map = new HashMap<Character, Character>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
    }

    /**
     * 2 ms	34.3 MB
     *
     * 跟 isValid2 的唯一区别:
     * 1. 空格全部替换掉
     *
     * 测试结果：无差别
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();

        s = s.replaceAll(" ", "");

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            switch (c) {
                case '(':
                case '[':
                case '{':
                    stack.push(c);
                    break;
                case ')':
                case ']':
                case '}':
                    if (stack.isEmpty()) {
                        return false;
                    } else {
                        char top = stack.pop();
                        if (map.get(c) != top) {
                            return false;
                        }
                    }
                    break;
                default:
                    break;
            }

        }

        return stack.empty();
    }

    /**
     * 2 ms	34.3 MB
     *
     * 跟 isValid1 的唯一区别就是map。一个是静态变量，一个是实体变量
     *
     * 测试结果：无差别
     *
     * @param s
     * @return
     */
    public boolean isValid2(String s) {
        Stack<Character> stack = new Stack<Character>();

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            switch (c) {
                case '(':
                case '[':
                case '{':
                    stack.push(c);
                    break;
                case ')':
                case ']':
                case '}':
                    if (stack.isEmpty()) {
                        return false;
                    } else {
                        char top = stack.pop();
                        if (map.get(c) != top) {
                            return false;
                        }
                    }
                    break;
                default:
                    break;
            }

        }

        return stack.empty();
    }

    /**
     * 2 ms	34.1 MB
     *
     *
     * @param s
     * @return
     */
    public boolean isValid1(String s) {
        Stack<Character> stack = new Stack<Character>();

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            switch (c) {
                case '(':
                case '[':
                case '{':
                    stack.push(c);
                    break;
                case ')':
                case ']':
                case '}':
                    if (stack.isEmpty()) {
                        return false;
                    } else {
                        char top = stack.pop();
                        if (PARENTHESES_MAP.get(c) != top) {
                            return false;
                        }
                    }
                    break;
                default:
                    break;
            }

        }

        return stack.empty();
    }
}
