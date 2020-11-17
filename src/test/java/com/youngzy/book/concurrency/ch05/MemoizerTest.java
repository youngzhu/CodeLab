package com.youngzy.book.concurrency.ch05;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.junit.Test;

import static org.junit.Assert.*;

public class MemoizerTest {

    /**
     * 串行
     */
    @Test
    public void test0() {
        ExpensiveFunction function = new ExpensiveFunction();


        System.out.println(new RandomDataGenerator().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE));
    }
}