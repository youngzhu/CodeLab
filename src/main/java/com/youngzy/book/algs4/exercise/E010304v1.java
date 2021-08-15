package com.youngzy.book.algs4.exercise;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.Map;

/**
 * 不谦虚地说，我的答案要比参考答案好
 * 参考答案是定义了6个常量，
 * 而我定义了一个map，两两之间有了对应关系
 *
 * 不过结构上，我的差了点
 * 我都写在main里了
 * 参考答案写了一个isBalance函数
 *
 * @author youngzy
 * @since 2021-08-08
 */
public class E010304v1 {

    static Map<Character, Character> MAP = new HashMap<Character, Character>(){
        {
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }
    };

    static boolean isBalance(String s) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else {
                if (!stack.isEmpty()) {
                    char top = stack.pop();
                    if (top != MAP.get(ch)) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        In in = new In("algs4/parentheses.txt");

        while (in.hasNextLine()) {
            String line = in.readLine();

            StdOut.println(isBalance(line));
        }
    }
}
