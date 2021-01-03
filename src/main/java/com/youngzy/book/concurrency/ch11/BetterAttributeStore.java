package com.youngzy.book.concurrency.ch11;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 11-5 减少锁的持有时间
 *
 * 通过缩小锁的作用范围，能极大地减少在持有锁时需要执行的指令数量
 */
public class BetterAttributeStore {
    private final Map<String, String> attributes = new HashMap<>();

    public boolean userLocationMatches(String name, String regexp) {
        String key = "user." + name + ".location";
        String location;
        synchronized (this) {
            location = attributes.get(key);
        }
        if (location == null)
            return false;
        else
            return Pattern.matches(regexp, location);
    }
}
