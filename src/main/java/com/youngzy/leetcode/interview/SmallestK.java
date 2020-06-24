package com.youngzy.leetcode.interview;

import com.youngzy.stackskills.datastructures.heap.MinimumHeap;

/**
 * 面试题 17.14. 最小K个数
 *
 * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
 *
 * 示例：
 * 输入： arr = [1,3,5,7,2,4,6,8], k = 4
 * 输出： [1,2,3,4]
 * 提示：
 *
 * 0 <= len(arr) <= 100000
 * 0 <= k <= min(100000, len(arr))
 *
 * 链接：https://leetcode-cn.com/problems/smallest-k-lcci
 */
public class SmallestK {
    public int[] smallestK(int[] arr, int k) {
        MaximumHeap heap = new MaximumHeap(k);

        /*
        首先用k值将堆填满
        root是最大的值，后面的值，如果大于root则忽略
        如果小于，则移除root，将新值插入堆中
         */
        for (int element : arr) {
            if (heap.isEmpty()) {
                heap.insert(element);
            } else if (! heap.isFull()
                    || heap.getHighestPriority() > element) {
                if (heap.isFull()) {
                    heap.removeHighestPriority();
                }

                heap.insert(element);
            }
        }

        return heap.data;
    }

    /**
     * 最大堆：node >= child
     * root 是所有元素中最大的
     */
    private class MaximumHeap {
        private int[] data;
        private int count;

        public MaximumHeap(int size) {
            data = new int[size];
            count = 0;
        }

        public boolean isEmpty() {
            return count == 0;
        }

        public boolean isFull() {
            return count == data.length;
        }

        /**
         * 将新元素放在末尾，然后慢慢上移，找到合适的位置
         * @param value
         */
        public void insert(int value) {
            if (isFull()) {
                // 也可以抛出异常
                return;
            }

            data[count] = value;
            siftUp(count);

            count++;
        }

        private void siftUp(int idx) {
            int parentIdx = getParentIndex(idx);

            // 如果当前节点比父节点大，则上移
            if (parentIdx != -1
                    && getElementAtIndex(idx) > getElementAtIndex(parentIdx)) {
                swap(parentIdx, idx);
                siftUp(parentIdx);
            }

        }

        public int getElementAtIndex(int idx) {
            return data[idx];
        }

        private void swap(int idx1, int idx2) {
            int tmp = data[idx1];
            data[idx1] = data[idx2];
            data[idx2] = tmp;
        }

        public int getLeftChildIndex(int index) {
            int result = 2 * index + 1;

            if (result >= count) {
                return -1;
            }

            return result;
        }

        public int getRightChildIndex(int index) {
            int result = 2 * index + 2;
            if (result >= count) {
                return -1;
            }

            return result;
        }

        public int getParentIndex(int index) {
            if (index < 0 || index > count) {
                return -1;
            }

            return (index - 1) / 2;
        }

        /**
         * 1. 移去第一个元素
         * 2. 将最后一个元素移到第一个
         * 3. 将第一个元素慢慢下移，找到合适的位置
         *
         * @return
         */
        private int removeHighestPriority() {
            int highest = getHighestPriority();

            data[0] = data[count - 1];
            count --;
            siftDown(0);

            return highest;
        }

        private void siftDown(int idx) {
            int leftIdx = getLeftChildIndex(idx);
            int rightIdx = getRightChildIndex(idx);

            int biggerIdx = -1;
            if (leftIdx != -1 && rightIdx != -1) {
                biggerIdx = getElementAtIndex(leftIdx) > getElementAtIndex(rightIdx)
                        ? leftIdx : rightIdx;
            } else if (leftIdx != -1) {
                biggerIdx = leftIdx;
            } else if (rightIdx != -1){
                biggerIdx = rightIdx;
            }

            if (biggerIdx == -1) {
                return;
            }

            // 如果当前节点有比他大的 子节点，则下移
            if (getElementAtIndex(biggerIdx) > getElementAtIndex(idx)) {
                swap(biggerIdx, idx);
                siftDown(biggerIdx);
            }

        }

        private int getHighestPriority() {
            if (isEmpty()) {
                return Integer.MIN_VALUE;
            }

            return data[0];
        }
    }
}
