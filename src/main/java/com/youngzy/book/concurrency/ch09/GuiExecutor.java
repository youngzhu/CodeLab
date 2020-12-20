package com.youngzy.book.concurrency.ch09;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 9-2 基于SwingUtilities构建的Executor
 */
public class GuiExecutor extends AbstractExecutorService {
    // 单例模式：私有的构造函数和公共的工厂方法
    private static final GuiExecutor instance = new GuiExecutor();

    private GuiExecutor() {}

    public static GuiExecutor getInstance() {
        return instance;
    }

    @Override
    public void execute(Runnable command) {
        if (SwingUtilities.isEventDispatchThread())
            command.run();
        else
            SwingUtilities.invokeLater(command);
    }

    @Override
    public void shutdown() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }
}
