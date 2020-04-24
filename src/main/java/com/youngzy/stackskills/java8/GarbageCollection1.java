package com.youngzy.stackskills.java8;

/**
 * 设置VM：
 * -verbose:gc -Xms11M -Xmx11M -Xmn11M -Xlog:gc* -XX:SurvivorRatio=8
 *
 * 本来是想实验一下 将对象赋值null 会不会加快回收
 * 但没看出差别。
 * 可能是我设计的不对
 * 也可能是我不会看
 */
public class GarbageCollection1 {
    static final int counter = 1100;

    public static void main(String[] args) {

        show();
//        showNoReference();

//        showAssignNull();
    }

    static void show() {
        int i = 0;
        while (i < counter) {
            AA a = new AA();
            i ++;
        }
    }

    static void showNoReference() {
        int i = 0;
        while (i < counter) {
            new AA();
            i ++;
        }
    }

    static void showAssignNull() {
        int i = 0;
        while (i < counter) {
            AA a = new AA();
            i ++;
            a = null;
        }
    }

}

class AA {
    static final int _1MB = 1024 * 1024;

    static int createdNo = 0;
    static int destroyNo = 0;

    byte[] b;

    AA() {
        b = new byte[_1MB];
        createdNo++;
        System.out.println("A is created, no:" + createdNo);
    }

    /**
     * Java 9 以后，这个方法就不用了
     *
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
//        super.finalize();
        destroyNo++;
        System.out.println("AAA is destroyed, no:" + destroyNo);
    }
}