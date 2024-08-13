package com.youngzy.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class XMLsTest {

    @Test
    void extractBody_success() {
        assertAll(
                () -> assertEquals(null, XMLs.extractBody(null)),
                () -> assertEquals(null, XMLs.extractBody("")),
                () -> assertEquals("abc", XMLs.extractBody("<body>abc</body>")),
                () -> assertEquals("abc", XMLs.extractBody("<s:Body>abc</s:Body>")),
                () -> assertEquals("<somebody>Jerry</somebody>",
                        XMLs.extractBody("<s:Body><somebody>Jerry</somebody></s:Body>")),
                () -> assertEquals("<somebody>Jerry</somebody>",
                        XMLs.extractBody("<soap:Body><somebody>Jerry</somebody></soap:Body>")),
                () -> assertTrue(1 == 1)
        );
    }

    @Test
    void extractBody_fail() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> XMLs.extractBody("abc")),
                () -> assertThrows(IllegalArgumentException.class, () -> XMLs.extractBody("<somebody>")),
                () -> assertTrue(1 == 1)
        );
    }

}