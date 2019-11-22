package com.youngzy.leetcode.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 * 示例:
 *
 * s = "3[a]2[bc]", 返回 "aaabcbc".
 * s = "3[a2[c]]", 返回 "accaccacc".
 * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
 *
 * 链接：https://leetcode-cn.com/problems/decode-string
 */
public class DecodeString {

    /**
     * 借鉴大神
     * 1. k 通过计算获得。差别不大
     *
     * @param s
     * @return
     */
    public String decodeString(String s) {
        StringBuffer sb = new StringBuffer();

        Stack<Character> stack = new Stack<Character>();

        StringBuffer encodedStr;
        int k, count;

        for (char c : s.toCharArray()) {
            // 不是 ] 全部入栈
            if (c != ']') {
                stack.push(c);
            } else {
                boolean flag = false; // 遇到了数字

                k = 0;
                count = 0;
                encodedStr = new StringBuffer();

                while (! stack.isEmpty()){
                    if (stack.peek() >= '0' && stack.peek() <= '9') {
                        flag = true;

                        k += Math.pow(10, count++) * Integer.parseInt("" + stack.pop());
                    } else {

                        if (flag) {
                            // 遇到数字后又遇到字母，表示一个 k[encoded_string] 结束
                            break;
                        }

                        if (stack.peek() == '[') {
                            stack.pop();
                        } else {
                            encodedStr.insert(0, stack.pop());
                        }
                    }

                }

                if (! stack.isEmpty()) {
                    // 如果还没结束，说明是个嵌套
                    // 需把刚刚处理的字符串重新入栈
                    for (int i = 0; i < k; i++) {
                        for (char ch : encodedStr.toString().toCharArray()) {
                            stack.push(ch);
                        }
                    }

                } else {

                    for (int i = 0; i < k; i++) {
                        sb.append(encodedStr);
                    }
                }

            }
        }

        int length = sb.length();
        while (! stack.isEmpty()) {
            // 如果还没结束，代表有普通字符串，直接加在后面
            sb.insert(length, stack.pop());

        }

        return sb.toString();
    }

    /**
     * StringBuffer VS StringBuilder，没太大差别
     * @param s
     * @return
     */
    public String decodeString2(String s) {
        StringBuffer sb = new StringBuffer();

        Stack<Character> stack = new Stack<Character>();

        StringBuffer encodedStr, k;

        for (char c : s.toCharArray()) {
            // 不是 ] 全部入栈
            if (c != ']') {
                stack.push(c);
            } else {
                boolean flag = false; // 遇到了数字

                k = new StringBuffer();
                encodedStr = new StringBuffer();

                while (! stack.isEmpty()){
                    if (stack.peek() >= '0' && stack.peek() <= '9') {
                        flag = true;

                        k.insert(0, stack.pop());
                    } else {

                        if (flag) {
                            // 遇到数字后又遇到字母，表示一个 k[encoded_string] 结束
                            break;
                        }

                        if (stack.peek() == '[') {
                            stack.pop();
                        } else {
                            encodedStr.insert(0, stack.pop());
                        }
                    }

                }

//                int kNum = Integer.valueOf(k.toString());
                int kNum = Integer.parseInt(k.toString());

                if (! stack.isEmpty()) {
                    // 如果还没结束，说明是个嵌套
                    // 需把刚刚处理的字符串重新入栈
                    for (int i = 0; i < kNum; i++) {
                        for (char ch : encodedStr.toString().toCharArray()) {
                            stack.push(ch);
                        }
                    }

                } else {

                    for (int i = 0; i < kNum; i++) {
                        sb.append(encodedStr);
                    }
                }

            }
        }

        int length = sb.length();
        while (! stack.isEmpty()) {
            // 如果还没结束，代表有普通字符串，直接加在后面
            sb.insert(length, stack.pop());

        }

        return sb.toString();
    }

    public String decodeString1(String s) {
        StringBuilder sb = new StringBuilder();

        Stack<Character> stack = new Stack<Character>();

        StringBuilder encodedStr, k;

        for (char c : s.toCharArray()) {
            // 不是 ] 全部入栈
            if (c != ']') {
                stack.push(c);
            } else {
                boolean flag = false; // 遇到了数字

                k = new StringBuilder();
                encodedStr = new StringBuilder();

                while (! stack.isEmpty()){
                    if (stack.peek() >= '0' && stack.peek() <= '9') {
                        flag = true;

                        k.insert(0, stack.pop());
                    } else {

                        if (flag) {
                            // 遇到数字后又遇到字母，表示一个 k[encoded_string] 结束
                            break;
                        }

                        if (stack.peek() == '[') {
                            stack.pop();
                        } else {
                            encodedStr.insert(0, stack.pop());

                        }
                    }

                }

                if (! stack.isEmpty()) {
                    // 如果还没结束，说明是个嵌套
                    // 需把刚刚处理的字符串重新入栈
                    for (int i = 0; i < new Integer(k.toString()); i++) {
                        for (char ch : encodedStr.toString().toCharArray()) {
                            stack.push(ch);
                        }
                    }

                } else {

                    for (int i = 0; i < new Integer(k.toString()); i++) {
                        sb.append(encodedStr);
                    }
                }

            }
        }

        int length = sb.length();
        while (! stack.isEmpty()) {
            // 如果还没结束，代表有普通字符串，直接加在后面
            sb.insert(length, stack.pop());

        }

        return sb.toString();
    }

    public static void main(String[] args) {
        DecodeString ds = new DecodeString();

        System.out.println("Start...");

        System.out.println(ds.decodeString("3[a]2[bc]"));

        System.out.println("End.");
    }
}
