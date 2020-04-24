package com.youngzy.stackskills.java8;

/**
 * 设置VM：-verbose:gc -Xms20M -Xmn10m -XX:+PrintGCDetails
 */
public class GarbageCollection {
    static final int counter = 1000000;

    public static void main(String[] args) {

        showA();
        showB();
        showC();

//        System.out.println("A  created " + A.createdNo + ", destroyed " + A.destroyNo);
//        System.out.println("B  created " + B.createdNo + ", destroyed " + B.destroyNo);
//        System.out.println("C  created " + C.createdNo + ", destroyed " + C.destroyNo);
    }

    static void showA() {
        int i = 0;
        while (i < counter) {
            A a = new A();
            i ++;
        }
    }

    static void showB() {
        int i = 0;
        while (i < counter) {
            new B();
            i ++;
        }
    }

    static void showC() {
        int i = 0;
        while (i < counter) {
            C c = new C();
            i ++;
            c = null;
        }
    }
}

class A {
    static int createdNo = 1;
    static int destroyNo = 1;
    A() {
        System.out.println("A is created, no:" + createdNo++);
    }

    {
//        System.out.println("initial block, 先于构造函数执行");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("AAA is destroyed, no:" + destroyNo++);
    }
}

class B {
    static int createdNo = 1;
    static int destroyNo = 1;
    B() {
        System.out.println("B is created, no:" + createdNo++);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("BBB is destroyed, no:" + destroyNo++);
    }
}

class C {
    static int createdNo = 1;
    static int destroyNo = 1;
    C() {
        System.out.println("C is created, no:" + createdNo++);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("CCC is destroyed, no:" + destroyNo++);
    }
}
