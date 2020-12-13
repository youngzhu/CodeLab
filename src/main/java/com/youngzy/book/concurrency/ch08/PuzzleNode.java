package com.youngzy.book.concurrency.ch08;

import java.util.LinkedList;
import java.util.List;

/**
 * 8-14 用于谜题解决框架的链表节点
 */
public class PuzzleNode<P, M> {
    final P position;
    final M move;
    final PuzzleNode<P, M> prev;

    PuzzleNode(P position, M move, PuzzleNode<P, M> prev) {
        this.position = position;
        this.move = move;
        this.prev = prev;
    }

    List<M> asMoveList() {
        List<M> solution = new LinkedList<>();
        for (PuzzleNode<P, M> n = this; n.move != null; n = prev)
            solution.add(0, n.move);
        return solution;
    }
}
