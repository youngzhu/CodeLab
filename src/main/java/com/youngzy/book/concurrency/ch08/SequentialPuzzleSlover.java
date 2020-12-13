package com.youngzy.book.concurrency.ch08;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 8-15 串行的谜题解答器
 */
class SequentialPuzzleSolver <P, M> {
    private final Puzzle<P, M> puzzle;
    private final Set<P> seen = new HashSet<>();

    public SequentialPuzzleSolver(Puzzle<P, M> puzzle) {
        this.puzzle = puzzle;
    }

    public List<M> solve() {
        P position = puzzle.initialPosition();
        return search(new PuzzleNode<P, M>(position, null, null));
    }

    private List<M> search(PuzzleNode<P, M> node) {
        if (! seen.contains(node.position)) {
            seen.add(node.position);
            if (puzzle.isGoal(node.position))
                return node.asMoveList();
            for (M move : puzzle.legalMoves(node.position)) {
                P pos = puzzle.move(node.position, move);
                PuzzleNode<P, M> child = new PuzzleNode<>(pos, move, node);
                List<M> result = search(child);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }
}
