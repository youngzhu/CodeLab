package com.youngzy.java.defaultmethod;

import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultMethodTest {

    @Test
    public void test() {
        assertEquals(DaPengWithFly.DAPENG_SAYING, new DaPengWithFly().fly());
        assertEquals(Bird.BIRD_SAYING, new DaPengWithoutFly().fly());
//        assertEquals(Flyable.FLYABLE_SAYING, new DaPengPlugin().fly()); // 失败
        assertEquals(Bird.BIRD_SAYING, new DaPengPlugin().fly());

        assertEquals(Flyable.FLYABLE_SAYING, new Airplane().fly());
    }
}