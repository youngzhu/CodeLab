package com.youngzy.stackskills.java8;

public class HelloString {
    /**
     * == 判断的是：是否指向同一个对象
     * equals 判断的是：内容是否一样
     * @param args
     */
    public static void main(String[] args) {
        String s1 = "Java";
        String s2 = "Java";

        // 都是指向对象池 String Pool
        // s1 == s2
        // s1 equals s2
        showResult(s1, s2);

        s1 = new String("Java");
        s2 = new String("Java");
        // new 会在内存中开辟一块新区域 heap
        //s1 != s2
        //s1 equals s2
        showResult(s1, s2);

        s1 = "hello " + s1;
        s2 = "hello " + s2;
        // 相当于 new
        //s1 != s2
        //s1 equals s2
        showResult(s1, s2);

        s1 = s1.intern();
        s2 = s2.intern();
        // intern，如果池中有内容一样的字符串，则指向池中
        // 如果没有，则加入池中，并返回其引用
        //s1 == s2
        //s1 equals s2
        showResult(s1, s2);

        // 挨个字母比较
        s1.compareTo(s2);
    }

    private static void showResult(String s1, String s2) {
        if (s1 == s2) {
            System.out.println("s1 == s2");
        } else {
            System.out.println("s1 != s2");
        }

        if (s1.equals(s2))
            System.out.println("s1 equals s2");
        else
            System.out.println("s1 not equals s2");

    }
}
