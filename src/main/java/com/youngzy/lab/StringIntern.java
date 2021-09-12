package com.youngzy.lab;

/**
 * @author youngzy
 * @since 2021-09-12
 */
public class StringIntern {
    // 大概是jdk优化过了，居然都是true
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "1".intern();
        String s3 = "abc";

//        System.out.println(s1 == s2);
//        System.out.println(s2 == s3);
        System.out.println(s1 == s3);
    }
}
