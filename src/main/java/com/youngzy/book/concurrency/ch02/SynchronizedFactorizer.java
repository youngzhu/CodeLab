package com.youngzy.book.concurrency.ch02;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 2-6 使用内置锁可以有效使用缓存，但是性能差（不要这么做）
 */
public class SynchronizedFactorizer extends GenericServlet implements Servlet {
    private BigInteger lastNumber;
    private BigInteger[] lastFactors;

    @Override
    public synchronized void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger bi = extractFromRequest(servletRequest);
        if (bi.equals(lastNumber)) {
            // 命中缓存，直接返回
            encodeIntoResponse(servletResponse, lastFactors);
        } else {
            BigInteger[] factors = factor(bi);
            // 这里，2个 set 不能保证原子性
            lastNumber = bi;
            lastFactors = factors;
            encodeIntoResponse(servletResponse, factors);
        }
    }

    private void encodeIntoResponse(ServletResponse servletResponse, BigInteger[] factors) {
    }

    private BigInteger[] factor(BigInteger bi) {
        // doesn't really factor
        return new BigInteger[] {bi};
    }

    private BigInteger extractFromRequest(ServletRequest servletRequest) {
        return new BigInteger("7");
    }

}
