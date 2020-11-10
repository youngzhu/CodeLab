package com.youngzy.book.concurrency.ch03;

import java.util.HashSet;
import java.util.Set;

/**
 * 3-5 发布一个对象
 *
 * 发布对象最简单的方法是将对象的引用保存到一个公有的
 * 静态变量中，以便任何类和线程都能看见该对象。
 */
public class Secrets {
    public static Set<Secret> knownSecrets;

    public void init() {
        knownSecrets = new HashSet<>();
    }

}

class Secret {

}