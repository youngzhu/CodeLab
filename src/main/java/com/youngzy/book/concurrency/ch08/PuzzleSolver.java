package com.youngzy.book.concurrency.ch08;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 8-18 可解决"无解"的解答器
 */
public class PuzzleSolver<P, M> extends ConcurrentPuzzleSolver<P, M> {
    PuzzleSolver(Puzzle<P, M> puzzle) {
        super(puzzle);
    }

    private final AtomicInteger taskCount = new AtomicInteger(0);

    protected Runnable newTask(P pos, M move, PuzzleNode<P, M> node) {
        return new CountingSolverTask(pos, move, node);
    }

    class CountingSolverTask extends SolverTask {
        CountingSolverTask(P pos, M move, PuzzleNode prev) {
            super(pos, move, prev);
            taskCount.incrementAndGet();
        }

        @Override
        public void run() {
            try {
                super.run();
            } finally {
                // 记录任务的数量，当值为0时表示都遍历过了，但没找到答案
                // 将答案设置为null
                if (taskCount.decrementAndGet() == 0) {
                    solution.setValue(null);
                }
            }
        }
    }

}
