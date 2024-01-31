package com.youngzy.java.defaultmethod.plugin;

/**
 * 用鳃呼吸
 *
 * @author youngzy
 * @since 2024-01-31
 */
public interface GillBreatheSupplier extends BreatheSupplier{
    static final String GILL_BREATHE = "用鳃呼吸";

    @Override
    default String breathe() {
        return GILL_BREATHE;
    }
}
