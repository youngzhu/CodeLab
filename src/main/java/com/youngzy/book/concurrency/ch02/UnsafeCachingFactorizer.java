package com.youngzy.book.concurrency.ch02;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 2-5 在没有足够的原子性保证的情况下对最后的一次计算结果进行缓存（不要这么做）
 *
 * 虽然Atomic本身是原子操作，是线程安全的
 * 但这里 lastNumber lastFactors 是一个组合，两者必须保持一致
 *
 * ！！要保持状态的一致性，就需要在单个原子操作中更新所有相关的状态变量
 */
public class UnsafeCachingFactorizer extends GenericServlet implements Servlet {
    private final AtomicReference<BigInteger> lastNumber = new AtomicReference<>();
    private final AtomicReference<BigInteger[]> lastFactors = new AtomicReference<>();

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger bi = extractFromRequest(servletRequest);
        if (bi.equals(lastNumber.get())) {
            // 命中缓存，直接返回
            encodeIntoResponse(servletResponse, lastFactors.get());
        } else {
            BigInteger[] factors = factor(bi);
            // 这里，2个 set 不能保证原子性
            lastNumber.set(bi);
            lastFactors.set(factors);
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
