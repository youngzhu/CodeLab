package com.youngzy.book.concurrency.exercise.ex02;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class FooService {
    public void execute(int[] data) {
        check();
        doBusiness(data);
        oneMoreThing();
        lastButNotLeast();
    }

    private void check() {

    }

    private void doBusiness(int[] data) {
        // insertTableA
        // insertTableB
        // insertTableC
    }

    /**
     * 随机地要么休眠5s，要么什么都不做
     */
    private void oneMoreThing() {
        long millis = System.currentTimeMillis();
        if (millis % 2 == 0) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 随机休眠3-5秒
     */
    private void lastButNotLeast() {
        Random random = new Random();
        int sleepMillis = 3000 + random.nextInt(2000);
        try {
            TimeUnit.MILLISECONDS.sleep(sleepMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
