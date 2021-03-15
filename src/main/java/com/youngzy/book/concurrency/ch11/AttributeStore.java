package com.youngzy.book.concurrency.ch11;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 11-4 将一个锁不必要地持有过长时间
 */
public class AttributeStore {
    private final Map<String, String> attributes = new HashMap<>();

    public synchronized boolean userLocationMatches(String name, String regexp) {
        String key = "user." + name + ".location";
        // 事实上只有get方法才真正需要锁
        String location = attributes.get(key);
        if (location == null) {
            return false;
        } else
            return Pattern.matches(regexp, location);
    }

}
