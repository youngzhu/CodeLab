package com.youngzy.book.concurrency.ch02;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * 2-1 一个无状态的Servlet
 *
 * 无状态（没有共享变量）的对象一定是线程安全的
 */
public class StatelessFactorizer extends GenericServlet implements Servlet {

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        BigInteger bi = extractFromRequest(servletRequest);
        BigInteger[] factors = factor(bi);
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
