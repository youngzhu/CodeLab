package com.youngzy.java.defaultmethod.plugin;

/**
 * 用肺呼吸
 *
 * @author youngzy
 * @since 2024-01-31
 */
public interface LungBreatheSupplier extends BreatheSupplier{
    static final String LUNG_BREATHE = "用肺呼吸";

    @Override
    default String breathe() {
        return LUNG_BREATHE;
    }
}
