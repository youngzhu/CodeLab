package com.youngzy.book.pragprog.ch04;

public class JavaAssert {
    public static void doSomething() {
        Object obj = null;
        // 需要在vm参数总启用才有用：-enableassertions
        assert obj != null : "not null";
    }

    public static void main(String[] args) {
        doSomething();
    }
}
