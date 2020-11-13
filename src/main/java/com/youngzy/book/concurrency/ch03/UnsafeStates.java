package com.youngzy.book.concurrency.ch03;

/**
 * 3-6 使内部的可变状态逸出（不要这么做）
 *
 * 任何调用者都能修改这个数组的内容。
 * 这个本该私有的变量已经逸出了它的作用域
 */
public class UnsafeStates {
    private String[] states = new String[]{
            "AK", "AL"
    };

    public String[] getStates() {
        return states;
    }
}
