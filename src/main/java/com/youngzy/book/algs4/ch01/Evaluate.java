package com.youngzy.book.algs4.ch01;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Dijkstra的双栈算术表达式求值算法
 *
 * @author youngzy
 * @since 2021-08-07
 */
public class Evaluate {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();

        while (!StdIn.isEmpty()) {
            // 读取字符
            String s = StdIn.readString();
            // 如果是运算符则压入栈
            if ("(".equals(s)) ;// 忽略
            else if ("+".equals(s)) ops.push(s);
            else if ("-".equals(s)) ops.push(s);
            else if ("*".equals(s)) ops.push(s);
            else if ("/".equals(s)) ops.push(s);
            else if (")".equals(s)) {
                // 如果是右括号，弹出运算符和操作数，并将计算结果压入栈
                String op = ops.pop();
                double v = vals.pop();
                if ("+".equals(op)) v = vals.pop() + v;
                else if ("-".equals(op)) v = vals.pop() - v;
                else if ("*".equals(op)) v = vals.pop() * v;
                else if ("/".equals(op)) v = vals.pop() / v;

                vals.push(v);
            }
            else { // 其他的都是数字，压入操作数栈
                vals.push(Double.valueOf(s));
            }
        }
        StdOut.println(vals.pop());
    }
}
