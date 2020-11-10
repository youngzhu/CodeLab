package com.youngzy.book.concurrency.ch03;


/**
 * 3-14 不安全的发布
 */
public class StuffIntoPublic {
    // 不安全
    public Holder holder;

    public void init() {
        holder = new Holder();
    }

    class Holder{}
}
