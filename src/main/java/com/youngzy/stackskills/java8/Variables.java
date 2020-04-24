package com.youngzy.stackskills.java8;

public class Variables {
    int x = f1();
    static int y = f2();

    private int f1() {
        System.out.println("init x");
        return 0;
    }

    private static int f2() {
        System.out.println("init y");
        return 1;
    }

    public static void main(String[] args) {

        Variables v1 = new Variables();
        Variables v2 = new Variables();
    }
}

/*
main 为空方法时，执行，只打印：init y

new 一个对象 打印：
init y
init x

new 两个对象 打印：
init y
init x
init x
 */
