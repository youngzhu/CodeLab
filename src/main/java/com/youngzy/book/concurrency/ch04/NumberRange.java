package com.youngzy.book.concurrency.ch04;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 4-10 不足以满足它的不变性条件（不要这么做）
 *
 * 虽然AtomicInteger是线程安全的，但经过组合得到的类却不是。
 * 因为状态变量 lower 和 upper 不是彼此独立的，
 * 所以不能把NumberRange的线程安全委托给它的状态变量
 */
public class NumberRange {
    // 不变性条件：lower <= upper
    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(0);

    public void setLower(int i) {
        // !!! 不安全的"先检查后执行"
        if (i > upper.get()) {
            throw new IllegalArgumentException("can't set lower to " + i);
        }
        lower.set(i);
    }

    public void setUpper(int i) {
        // !!! 不安全的"先检查后执行"
        if (i < lower.get()) {
            throw new IllegalArgumentException("can't set upper to " + i);
        }
        upper.set(i);
    }

    public boolean isInRange(int i) {
        return i >= lower.get() && i <= upper.get();
    }
}
