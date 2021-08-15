package com.youngzy.book.algs4.exercise;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.Map;

/**
 * 编写一个Stack的用例Parentheses，从标准输入中读取一个文本流并使用栈判定其中的括号
 * 是否配对完整。
 *
 * 例如，
 * [()]{}{[()()]()}，打印 true
 * [(])，打印false
 *
 * @author youngzy
 * @since 2021-08-08
 */
public class E010304 {

    static Map<Character, Character> MAP = new HashMap<Character, Character>(){
        {
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }
    };

    public static void main(String[] args) {
        In in = new In("algs4/parentheses.txt");

        while (in.hasNextLine()) {
            String line = in.readLine();

            Stack<Character> stack = new Stack<>();
            boolean result = true;
            for (char ch : line.toCharArray()) {
                if (ch == '(' || ch == '[' || ch == '{') {
                    stack.push(ch);
                } else {
                    if (!stack.isEmpty()) {
                        char top = stack.pop();
                        if (top != MAP.get(ch)) {
                            result = false;
                            break;
                        }
                    } else {
                        result = false;
                        break;
                    }
                }
            }

            StdOut.println(result && stack.isEmpty());
        }
    }
}
