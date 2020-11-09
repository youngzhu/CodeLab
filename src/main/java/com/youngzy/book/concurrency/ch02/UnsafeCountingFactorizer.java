package com.youngzy.book.concurrency.ch02;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * 2-2 在没有同步的情况下统计请求次数（不要这么做）
 */
public class UnsafeCountingFactorizer extends GenericServlet implements Servlet {
    private long count = 0;

    public long getCount() {
        return count;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger bi = extractFromRequest(servletRequest);
        BigInteger[] factors = factor(bi);
        ++count;
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
