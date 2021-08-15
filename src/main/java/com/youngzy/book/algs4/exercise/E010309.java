package com.youngzy.book.algs4.exercise;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 有一个缺少做括号的中缀表达式
 * 将其补全，并打印出来
 * <p>
 * 输入：
 * 1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
 * 输出
 * ( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )
 *
 * @author youngzy
 * @since 2021-08-08
 */
public class E010309 {
    private static final char LEFT_PAREN = '(';
    private static final char RIGHT_PAREN = ')';

    public static void main(String[] args) {
        In in = new In("algs4/e010309.txt");
        String input = in.readLine();
        StdOut.println(input);
        String outputExpected = in.readLine();
        StdOut.println(outputExpected);

        while (in.hasNextChar())
            StdOut.print(in.readChar());

        String outputActual = complete(input);
        StdOut.println(outputActual);
    }

    private static String complete(String input) {
        Stack<Character> stack = new Stack<>(); // 主栈
        Stack<Character> helper = new Stack<>(); // 辅助栈


        for (char ch : input.toCharArray()) {
            if (ch == ' ')
                continue;

            int leftParenCount = 0; // 左括号的个数
            int opCount = 0; // 操作符的个数
            int valCount = 0; // 操作数的个数

            if (ch != RIGHT_PAREN) {
                stack.push(ch);
            } else {
                helper.push(ch);

                while (!stack.isEmpty()) {
                    char c = stack.pop();

                    helper.push(c);
                    if (!isOp(c) && !isParen(c)) { // 数字
                        valCount++;

                        if (valCount % 2 == 0 // 完整的表达式应该有成对的操作数
                                && leftParenCount < opCount // 左括号数（括号对数）应该等于操作符数
                        ) {
                            boolean flag = true; // 是否应该补左括号
                            while (!stack.isEmpty() && stack.peek() == LEFT_PAREN) {
                                helper.push(stack.pop());
                                leftParenCount++;
                            }
                            if (leftParenCount == opCount)
                                flag = false;

                            if (flag) {
                                stack.push(LEFT_PAREN);
                                break;
                            }
                        }
                    } else if (isOp(c)) {
                        opCount++;
                    } else if (c == LEFT_PAREN) {
                        leftParenCount++;
                    }


                }

                while (!helper.isEmpty()) {
                    stack.push(helper.pop());
                }
            }
        }

        while (!stack.isEmpty()) {
            helper.push(stack.pop());
        }

        return helper.toString();
    }

    // 是否为操作符
    private static boolean isOp(char ch) {
        return ch == '+' || ch == '-'
                || ch == '*' || ch == '/';
    }

    // 是否为括号
    private static boolean isParen(char ch) {
        return ch == LEFT_PAREN || ch == RIGHT_PAREN;
    }
}
