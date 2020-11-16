package com.youngzy.book.concurrency.ch05;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * 5-20 在因式分解 servlet 中使用 Memorizer来缓存结果
 */
public class Factorizer extends GenericServlet implements Servlet {
    private final Computable<BigInteger, BigInteger[]> c = new Computable<BigInteger, BigInteger[]>() {
        @Override
        public BigInteger[] compute(BigInteger arg) throws InterruptedException {
            return factor(arg);
        }
    };

    private final Computable<BigInteger, BigInteger[]> cache = new MemoizerFinal<>(c);

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        try {
            BigInteger i = extractFromRequest(servletRequest);
            encodeIntoResponse(servletResponse, cache.compute(i));
        } catch (InterruptedException e) {
            encodeError(servletResponse, "factorization interrupted");
        }
    }

    void encodeError(ServletResponse response, String errorStr) {}

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
