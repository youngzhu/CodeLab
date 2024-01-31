package com.youngzy.java.defaultmethod;

/**
 * @author youngzy
 * @since 2024-01-31
 */
public class DaPengWithFly extends Bird {
    static final String DAPENG_SAYING = "扶摇直上九万里";

    @Override
    public String fly() {
        return DAPENG_SAYING;
    }
}
