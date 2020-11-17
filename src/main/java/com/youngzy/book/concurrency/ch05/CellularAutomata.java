package com.youngzy.book.concurrency.ch05;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 5-15 通过 CyclicBarrier 协调细胞自动衍生系统中的计算
 *
 * 为每一个细胞分配一个线程是不现实的，因为这将产生过多的线程，
 * 而在协调这些线程上导致的开销将降低整体性能。
 *
 * 合理的做法是，将线程分解成一定数量的子问题，为每个子问题分配一个线程，
 * 之后再把所有的结果合并起来。
 *
 * 在每个步骤中，工作线程都为各自子问题中的所有细胞计算新值。当所有工作线程都到达栅栏时，
 * 栅栏会把这些新值提交给数据模型。
 *
 * 当栅栏操作执行完成后，将开始下一轮计算
 */
public class CellularAutomata {
    private final Board mainBoard;
    private final CyclicBarrier barrier;
    private final Worker[] workers;

    public CellularAutomata(Board board) {
        this.mainBoard = board;
        int count = Runtime.getRuntime().availableProcessors();
        this.barrier = new CyclicBarrier(count,
                new Runnable() {
                    @Override
                    public void run() {
                        mainBoard.commitNewValues();
                    }
                });
        this.workers = new Worker[count];
        for (int i = 0; i < count; i++) {
            workers[i] = new Worker(mainBoard.getSubBoard(count, i));
        }
    }

    private class Worker implements Runnable {
        private final Board board;

        public Worker(Board board) {
            this.board = board;
        }

        @Override
        public void run() {
            while (! board.hasConverged()) {
                for (int x = 0; x < board.getMaxX(); x++) {
                    for (int y = 0; y < board.getMaxY(); y++) {
                        board.setNewValue(x, y, computeValue(x, y));
                    }
                }

                try {
                    barrier.await();
                } catch (InterruptedException interruptedException) {
                    return;
                } catch (BrokenBarrierException brokenBarrierException) {
                    return;
                }

            }
        }

        private int computeValue(int x, int y) {
            return 0;
        }
    }

    public void start() {
        for (int i = 0; i < workers.length; i++) {
            new Thread(workers[i]).start();
        }

        mainBoard.waitForConvergence();
    }

    interface Board {
        int getMaxX();
        int getMaxY();
        int getValue(int x, int y);
        int setNewValue(int x, int y, int value);
        void commitNewValues();
        boolean hasConverged();
        void waitForConvergence();
        Board getSubBoard(int numPartitions, int index);
    }
}
