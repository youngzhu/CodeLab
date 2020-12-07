package com.youngzy.book.concurrency.ch07;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 7-13 不支持关闭的生产者-消费者日志服务
 */
public class LogWriter {
    private final BlockingQueue<String> queue;
    private final LoggerThread logger;
    private static final int CAPACITY = 1000;

    public LogWriter(Writer writer) {
        this.queue = new LinkedBlockingQueue<>(CAPACITY);
        this.logger = new LoggerThread(writer);
    }

    public void start() {
        logger.start();
    }

    public void log(String msg) throws InterruptedException {
        queue.put(msg);
    }

    private class LoggerThread extends Thread {
        private final PrintWriter writer;

        public LoggerThread(Writer writer) {
            // autoflush
            this.writer = new PrintWriter(writer, true);
        }

        @Override
        public void run() {
            try {
                while (true)
                    writer.println(queue.take());
            } catch (InterruptedException ignored) {

            } finally {
                writer.close();
            }
        }
    }
}
