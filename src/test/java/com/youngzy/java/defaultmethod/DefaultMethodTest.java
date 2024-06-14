package com.youngzy.java.defaultmethod;

import com.youngzy.java.defaultmethod.plugin.Fish;
import com.youngzy.java.defaultmethod.plugin.GillBreatheSupplier;
import com.youngzy.java.defaultmethod.plugin.Human;
import com.youngzy.java.defaultmethod.plugin.LungBreatheSupplier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultMethodTest {

    @Test
    public void test() {
        assertEquals(DaPengWithFly.DAPENG_SAYING, new DaPengWithFly().fly());
        assertEquals(Bird.BIRD_SAYING, new DaPengWithoutFly().fly());
//        assertEquals(Flyable.FLYABLE_SAYING, new DaPengPlugin().fly()); // 失败
        assertEquals(Bird.BIRD_SAYING, new DaPengPlugin().fly());

        assertEquals(Flyable.FLYABLE_SAYING, new Airplane().fly());
    }

    @Test
    public void testPlugin() {
        assertEquals(LungBreatheSupplier.LUNG_BREATHE, new Human().breathe());
        assertEquals(GillBreatheSupplier.GILL_BREATHE, new Fish().breathe());
    }
}