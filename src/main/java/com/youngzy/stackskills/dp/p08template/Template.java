package com.youngzy.stackskills.dp.p08template;

abstract class Template {

    /**
     * 模版方法
     *
     * final，为了不让子类改变算法，要严格按照这个算法执行
     */
    final void templateMethods() {
        step1();
        step2();
        hook();
    }

    /**
     * 具体由子类实现
     */
    abstract void step1();
    abstract void step2();

    /**
     * 钩子
     *
     * 可为空
     * 可以由默认实现
     * 也可以由子类覆盖
     */
    void hook() {

    }

}
