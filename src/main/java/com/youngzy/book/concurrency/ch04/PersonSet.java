package com.youngzy.book.concurrency.ch04;

import java.util.HashSet;
import java.util.Set;

/**
 * 4-2 通过封闭机制来实现线程安全
 *
 * 实例封闭是构建线程安全类的最简单的一个方式
 */
public class PersonSet {
    // 封闭，不会逸出
    private final Set<Person> mySet = new HashSet<>();

    public synchronized void addPerson(Person person) {
        mySet.add(person);
    }

    public synchronized boolean containsPerson(Person person) {
        return mySet.contains(person);
    }

    interface Person {}
}
