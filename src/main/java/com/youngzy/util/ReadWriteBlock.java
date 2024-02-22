package com.youngzy.util;

import java.io.IOException;

/**
 * @author youngzy
 * @since 2024-02-22
 */
@FunctionalInterface
public interface ReadWriteBlock {
    void accept(ReadWriteEAM instance) throws IOException;
}