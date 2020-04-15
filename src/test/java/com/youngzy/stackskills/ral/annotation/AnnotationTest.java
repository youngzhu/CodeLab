package com.youngzy.stackskills.ral.annotation;

import org.junit.Test;

import static org.junit.Assert.*;

public class AnnotationTest {

    @Test
    public void introduceYourself() {
        IShape aShape = new Square();
        assertEquals("你可以叫我长方形，但它并不是我的真名。我的真名叫正方形。", aShape.introduceYourself());
    }
}