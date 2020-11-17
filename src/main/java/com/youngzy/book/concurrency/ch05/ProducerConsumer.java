package com.youngzy.book.concurrency.ch05;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 5-8 桌面搜索程序中的"生产者-消费者"
 */
public class ProducerConsumer {
    static class FileCrawler implements Runnable {
        private final BlockingDeque<File> fileQueue;
        private final FileFilter fileFilter;
        private final File root;

        public FileCrawler(BlockingDeque<File> fileQueue,
                           final FileFilter fileFilter,
                           File root) {
            this.fileQueue = fileQueue;
            this.root = root;
            this.fileFilter = new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.isDirectory() || fileFilter.accept(f);
                }
            };
        }

        public void run() {
            try {
                crawl(root);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        private void crawl(File root) throws InterruptedException {
            File[] entries = root.listFiles(fileFilter);
            if (entries != null) {
                for (File entry : entries) {
                    if (entry.isDirectory()) {
                        crawl(entry);
                    } else if (! alreadyIndexed(entry)){
                        fileQueue.push(entry);
                    }
                }
            }
        }

        private boolean alreadyIndexed(File file) {
            return false;
        }
    }

    static class Indexer implements Runnable {
        private final BlockingDeque<File> queue;

        public Indexer(BlockingDeque<File> queue) {
            this.queue = queue;
        }

        public void run() {
            try {
                while (true) {
                    indexFile(queue.take());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        public void indexFile(File file) {
            // index file
            System.out.println("index file:" + file.getAbsolutePath());
        }
    }

    private static final int BOUND = 10;
    private static final int NUM_OF_CONSUMERS = Runtime.getRuntime().availableProcessors();

    public static void startIndexing(File[] roots) {
        BlockingDeque<File> queue = new LinkedBlockingDeque<>(BOUND);
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return true;
            }
        };

        for (File root : roots) {
            Runnable producer = new FileCrawler(queue, fileFilter, root);
            new Thread(producer).start();
        }

        for (int i = 0; i < NUM_OF_CONSUMERS; i++) {
            Runnable consumer = new Indexer(queue);
            new Thread(consumer).start();
        }
    }
}
