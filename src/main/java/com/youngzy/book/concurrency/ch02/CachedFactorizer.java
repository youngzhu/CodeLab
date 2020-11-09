package com.youngzy.book.concurrency.ch02;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * 2-8 相比2-6
 * 该类实现了简单性（对整个方法进行同步）与并发性（对尽可能短的代码路径进行同步）之间的平衡
 */
public class CachedFactorizer extends GenericServlet implements Servlet {
    private BigInteger lastNumber;
    private BigInteger[] lastFactors;
    private long hits;
    private long cacheHits;

    public synchronized long getHits() {
        return hits;
    }

    public synchronized double getCacheHitRatio() {
        return (double) cacheHits / hits;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger bi = extractFromRequest(servletRequest);
        BigInteger[] factors = null;

        synchronized (this) {
            ++hits;
            if (bi.equals(lastNumber)) {
                // 命中缓存，直接返回
                ++cacheHits;
                factors = lastFactors.clone();
            }
        }

        if (factors == null) {
            // 类似这样的 执行时间较长的操作 要放在同步块的外面
            factors = factor(bi);

            synchronized (this) {
                lastNumber = bi;
                lastFactors = factors.clone();
            }
        }

        encodeIntoResponse(servletResponse, factors);
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
