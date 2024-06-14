package com.youngzy.stackskills.datastructures;

import com.youngzy.stackskills.datastructures.list.LinkedListVSArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class LinkedListVSArrayTest {

    private static final int SIZE = 1_000_000;
    private static final int RANDOM_ACCESS_TIMES = 1000;

    LinkedListVSArray la;

    @BeforeEach
    public void setUp() throws Exception {
        la = new LinkedListVSArray();
    }

    @Test
    public void testLoadData() {
        long timeStart = System.currentTimeMillis();
        Runtime runtime = Runtime.getRuntime();
        long memoryStart = runtime.freeMemory();

        la.loadDataWithArray(SIZE);

        long timeEnd = System.currentTimeMillis();
        long memoryEnd = runtime.freeMemory();

        System.out.println("Array, times: " + (timeEnd - timeStart) + ", memories:" + (memoryStart - memoryEnd));

        timeStart = System.currentTimeMillis();
        memoryStart = runtime.freeMemory();

        la.loadDataWithLinkedList(SIZE);

        timeEnd = System.currentTimeMillis();
        memoryEnd = runtime.freeMemory();

        System.out.println("LinkedList, times: " + (timeEnd - timeStart) + ", memories:" + (memoryStart - memoryEnd));

    }

    @Test
    public void testRandomAccess() {
        la.loadDataWithLinkedList(SIZE);
        Random random = new Random();

        int idx;

        long timeStart = System.currentTimeMillis();
        Runtime runtime = Runtime.getRuntime();
        long memoryStart = runtime.freeMemory();

        for (int i = 0; i < RANDOM_ACCESS_TIMES; i++) {
            idx = (int)(random.nextDouble() * SIZE);
            la.randomAccessWithLinkedList2(idx);
//            System.out.println("" + la.randomAccessWithLinkedList2(idx));
        }

        long timeEnd = System.currentTimeMillis();
        long memoryEnd = runtime.freeMemory();

        long listTimes = timeEnd - timeStart;
        long listMemory = memoryStart - memoryEnd;

        la.loadDataWithArray(SIZE);
        timeStart = System.currentTimeMillis();
        memoryStart = runtime.freeMemory();

        for (int i = 0; i < RANDOM_ACCESS_TIMES; i++) {
            idx = (int)(random.nextDouble() * SIZE);
            la.randomAccessWithArray(idx);
//            System.out.println("" + la.randomAccessWithArray(idx));
        }

        timeEnd = System.currentTimeMillis();
        memoryEnd = runtime.freeMemory();


        System.out.println("LinkedList, times: " + listTimes + ", memories:" + listMemory);
        System.out.println("Array, times: " + (timeEnd - timeStart) + ", memories:" + (memoryStart - memoryEnd));


    }
}