package com.youngzy.book.concurrency.ch08;

import com.youngzy.stackskills.datastructures.stack.Element;

import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * 8-10 将串行转化为并行
 */
public abstract class TransformingSequential {
    void processSequentially(List<Element> elements) {
        for (Element e : elements)
            process(e);
    }

    void processInParallel(Executor executor, List<Element> elements) {
        for (final Element e : elements)
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    process(e);
                }
            });
    }

    abstract void process(Element element);

    public <T> void sequentialRecursive(List<Node<T>> nodes, Collection<T> results) {
        for (Node<T> n : nodes) {
            results.add(n.compute());
            sequentialRecursive(n.getChildren(), results);
        }
    }

    public <T> void parallelRecursive(final Executor executor, List<Node<T>> nodes, final Collection<T> result) {
        // 遍历仍然是串行的
        // 只有 compute 是并行的
        for (final Node<T> n : nodes) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    result.add(n.compute());
                }
            });
            parallelRecursive(executor, n.getChildren(), result);
        }
    }

    /**
     * 该方法调用 parallelRecursive 获取所有的结果
     *
     * @param nodes
     * @param <T>
     * @return
     * @throws InterruptedException
     */
    public <T> Collection<T> getParallelResults(List<Node<T>> nodes) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Queue<T> resultQueue = new ConcurrentLinkedDeque<>();
        parallelRecursive(executorService, nodes, resultQueue);
        executorService.shutdownNow();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        return resultQueue;
    }

    interface Node<T> {
        T compute();
        List<Node<T>> getChildren();
    }
}
