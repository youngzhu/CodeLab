package com.youngzy.book.concurrency.ch08;

import java.util.Set;

/**
 * 8-13 搬箱子之类谜题的抽象类
 */
public interface Puzzle<P, M> {
    P initialPosition();
    boolean isGoal(P position);
    Set<M> legalMoves(P position);
    P move(P position, M move);
}
