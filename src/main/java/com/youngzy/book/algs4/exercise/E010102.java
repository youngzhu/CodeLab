package com.youngzy.book.algs4.exercise;

/**
 * @author youngzy
 * @since 2021-07-24
 */
public class E010102 {
    // 给出表达式的类型和值
    public static void main(String[] args) {
        Object value = (1 + 2.236) / 2;
        print(value);// double 1.618

        value = 1 + 2 + 3 + 4.0;
        print(value);// double 10.0

        value = 4.1 >= 4;
        print(value);// boolean true

        value = 1 + 2 + "3";
        print(value);// string 33
    }

    public static void print(Object v) {
        System.out.println(v.getClass().getTypeName() + " , " + v.toString());
    }
}
