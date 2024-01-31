package com.youngzy.java.defaultmethod;

/**
 * @author youngzy
 * @since 2024-01-31
 */
public interface Flyable {
    static final String FLYABLE_SAYING = "I can fly";

    default String fly() {
        return FLYABLE_SAYING;
    }
}
