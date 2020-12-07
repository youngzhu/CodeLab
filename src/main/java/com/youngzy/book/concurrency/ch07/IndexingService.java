package com.youngzy.book.concurrency.ch07;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 7-17 通过"毒丸"对象来关闭服务
 *
 * 毒丸，Poison Pill，是指一个放在队列上的对象，
 * 其含义是：当得到这个对象时，立即停止
 */
public class IndexingService {
    private static final int CAPACITY = 1000;
    private static final File POISON = new File("");

    private final IndexerThread consumer = new IndexerThread();
    private final CrawlerThread producer = new CrawlerThread();
    private final BlockingQueue<File> queue;
    private final FileFilter fileFilter;
    private final File root;

    public IndexingService(File root, final FileFilter fileFilter) {
        this.root = root;
        this.queue = new LinkedBlockingQueue<>(CAPACITY);
        this.fileFilter = new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || fileFilter.accept(f);
            }
        };
    }

    private boolean alreadyIndexed(File file) {
        return false;
    }

    /**
     * 生产者
     */
    class CrawlerThread extends Thread {
        @Override
        public void run() {
            try {
                crawl(root);
            } catch (InterruptedException e) {

            } finally {
                while (true) {
                    try {
                        queue.put(POISON);
                        break;
                    } catch (InterruptedException e) {
                        // 重新尝试
                    }
                }
            }
        }

        private void crawl(File root) throws InterruptedException {
            File[] entries = root.listFiles(fileFilter);
            if (entries != null) {
                for (File entry : entries) {
                    if (entry.isDirectory())
                        crawl(entry);
                    else if (! alreadyIndexed(entry))
                        queue.put(entry);
                }
            }
        }
    }

    /**
     * 消费者
     */
    class IndexerThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    File file = queue.take();
                    if (file == POISON)
                        break;
                    else
                        indexFile(file);
                }
            } catch (InterruptedException consumed) {

            }
        }

        public void indexFile(File file) {
            // 代码 5-8
        }
    }

    public void start() {
        producer.start();
        consumer.start();
    }

    public void stop() {
        producer.interrupt();
    }

    public void awaitTermination() throws InterruptedException {
        consumer.join();
    }
}
