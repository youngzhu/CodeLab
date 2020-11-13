package com.youngzy.book.concurrency.ch03;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * 3-12,13 使用不可变对象进行缓存
 */
public class VolatileCachedFactorizer extends GenericServlet implements Servlet {

    private volatile OneValueCache cache = new OneValueCache(null, null);

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger bi = extractFromRequest(servletRequest);
        BigInteger[] factors = cache.getFactors(bi);

        if (factors == null) {
            factors = factor(bi);

            cache = new OneValueCache(bi, factors);
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

class OneValueCache {
    private final BigInteger lastNumber;
    private final BigInteger[] lastFactor;

    public OneValueCache(BigInteger lastNumber, BigInteger[] lastFactor) {
        this.lastNumber = lastNumber;
        // 如果这里不使用copyOf，该类就不是不可变的
        this.lastFactor = Arrays.copyOf(lastFactor, lastFactor.length);
    }

    public BigInteger[] getFactors(BigInteger i) {
        if (lastNumber == null || !lastNumber.equals(i)) {
            return null;
        } else {
            return Arrays.copyOf(lastFactor, lastFactor.length);
        }
    }
}
