package com.youngzy.book.concurrency.ch03;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

/**
 * 3-11 在可变对象的基础上构建的不可变类
 */
public class ThreeStooges {
    // set 对象是可变的
    // 但是在构造完成之后无法对其进行修改
    private final Set<String> stooges = new HashSet<>();

    public ThreeStooges() {
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }

    public boolean isStooge(String name) {
        return stooges.contains(name);
    }

    public String getStoogeNames() {
        List<String> stooges = new Vector<>();
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");

        return stooges.toString();
    }
}
