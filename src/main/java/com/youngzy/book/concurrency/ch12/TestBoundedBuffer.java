package com.youngzy.book.concurrency.ch12;

import junit.framework.TestCase;

/**
 * 12-2，3,7 BoundedBuffer 的单元测试类
 */
public class TestBoundedBuffer extends TestCase {
    private static final long LOCKUP_DETECT_TIMEOUT = 1000;
    private static final int CAPACITY = 10000;
    private static final int THRESHOLD = 10000;

    /*
    基本测试
     */
    public void testIsEmptyWhenConstructed() {
        SemaphoreBoundedBuffer<Integer> sbb = new SemaphoreBoundedBuffer<>(10);
        assertTrue(sbb.isEmpty());
        assertFalse(sbb.isFull());
    }

    public void testIsFullAfterPuts() throws InterruptedException {
        SemaphoreBoundedBuffer<Integer> sbb = new SemaphoreBoundedBuffer<>(10);
        for (int i = 0; i < 10; i++)
            sbb.put(i);
        assertTrue(sbb.isFull());
        assertFalse(sbb.isEmpty());
    }

    /*
    测试阻塞行为以及对中断的响应性
     */
    public void testTakeBlocksWhenEmpty() {
        final SemaphoreBoundedBuffer<Integer> sbb = new SemaphoreBoundedBuffer(10);
        Thread taker = new Thread() {
            @Override
            public void run() {
                try {
                    int unused = sbb.take(); // 从空缓存中获取数据
                    fail(); // 如果执行到这里，表示出现了错误
                } catch (InterruptedException e) {

                }
            }
        };

        try {
            taker.start(); // 启动
            Thread.sleep(LOCKUP_DETECT_TIMEOUT); // 等待
            taker.interrupt(); // 中断
            taker.join(LOCKUP_DETECT_TIMEOUT); // 限时的join能确保测试最终完成
            assertFalse(taker.isAlive());
        } catch (Exception unexpected) {
            fail();
        }
    }

    /*
    12-7 测试资源泄露
     */
    class Big {
        double[] data = new double[100000];
    }

    public void testLeak() throws InterruptedException {
        SemaphoreBoundedBuffer<Big> sbb = new SemaphoreBoundedBuffer(CAPACITY);
        int heapSize1 = snapshotHeap();
        for (int i = 0; i < CAPACITY; i ++)
            sbb.put(new Big());
        for (int i = 0; i < CAPACITY; i++)
            sbb.take();
        int heapSize2 = snapshotHeap();
        assertTrue(Math.abs(heapSize1 - heapSize2) < THRESHOLD);
    }

    private int snapshotHeap() {
        return 0;
    }
}
