package com.youngzy.book.concurrency.ch08;

import java.util.List;
import java.util.concurrent.*;

/**
 * 8-16 并发的谜题解答器
 *
 * 存在一个问题：如果无解，程序将无法结束
 */
public class ConcurrentPuzzleSolver <P, M> {
    private final Puzzle<P, M> puzzle;
    private final ExecutorService executorService;
    private final ConcurrentMap<P, Boolean> seen;
    protected final ValueLatch<PuzzleNode<P, M>> solution = new ValueLatch<>();

    ConcurrentPuzzleSolver(Puzzle<P, M> puzzle) {
        this.puzzle = puzzle;
        this.executorService = initThreadPool();
        this.seen = new ConcurrentHashMap<>();
        if (executorService instanceof ThreadPoolExecutor) {
            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
            // 将拒绝执行处理器设置为"抛弃已提交的任务"
            threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        }
    }

    private ExecutorService initThreadPool() {
        return Executors.newCachedThreadPool();
    }

    public List<M> solve() throws InterruptedException {
        try {
            P pos = puzzle.initialPosition();
            executorService.execute(newTask(pos, null, null));
            // 阻塞，直到找到答案
            PuzzleNode<P, M> solnPuzzleNode = solution.getValue();
            return solnPuzzleNode == null ? null : solnPuzzleNode.asMoveList();
        } finally {
            executorService.shutdown();
        }
    }

    protected Runnable newTask(P pos, M move, PuzzleNode<P, M> node) {
        return new SolverTask(pos, move, node);
    }

    protected class SolverTask extends PuzzleNode<P, M> implements Runnable {
        SolverTask(P pos, M move, PuzzleNode prev) {
            super(pos, move, prev);
        }

        @Override
        public void run() {
            if (solution.isSet() || seen.putIfAbsent(position, true) != null)
                return; // 已经找到答案，或者已经遍历过该位置
            if (puzzle.isGoal(position))
                solution.setValue(this);
            else
                for (M m : puzzle.legalMoves(position)) {
                    executorService.execute(newTask(puzzle.move(position, m), m, this));
                }
        }
    }
}
