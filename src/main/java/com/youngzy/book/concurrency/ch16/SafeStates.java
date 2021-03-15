package com.youngzy.book.concurrency.ch16;

import java.util.HashMap;
import java.util.Map;

/**
 * 16-8 不可变对象的初始化安全性
 */
public class SafeStates {
    private final Map<String, String> states;

    public SafeStates() {
        states = new HashMap<>();
        states.put("alaska", "AK");
        states.put("alabama", "AL");
        //...
        states.put("wyoming", "WY");
    }

    public String getAbbreviation(String fullName) {
        return states.get(fullName);
    }
}
