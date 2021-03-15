package com.youngzy.book.concurrency.ch15;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 15-3 通过CAS来维持包含多个变量的不变性条件
 */
public class CASNumberRange {
    private static class IntPair {
        // 不变性条件：lower <= upper
        final int lower;
        final int upper;

        public IntPair(int lower, int upper) {
            this.lower = lower;
            this.upper = upper;
        }
    }

    private final AtomicReference<IntPair> values =
            new AtomicReference<>(new IntPair(0, 0));

    public int getLower() {
        return values.get().lower;
    }

    public int getUpper() {
        return values.get().upper;
    }

    public void setLower(int i) {
        while (true) {
            IntPair oldValue = values.get();
            if (i > oldValue.upper)
                throw new IllegalArgumentException("Can't set lower to " + i + " > upper");
            IntPair newValue = new IntPair(i, oldValue.upper);
            if (values.compareAndSet(oldValue, newValue))
                return;
        }
    }

    public void setUpper(int i) {
        while (true) {
            IntPair oldValue = values.get();
            if (i < oldValue.lower)
                throw new IllegalArgumentException(
                        "Can't set upper to " + i + " < lower");
            IntPair newValue = new IntPair(oldValue.lower, i);
            if (values.compareAndSet(oldValue, newValue))
                return;
        }
    }
}
