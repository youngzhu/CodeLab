package com.youngzy.stackskills.algorithms.sort;

/**
 * arr[index] >= arr[child]
 */
public class HeapSort extends Sort {
    @Override
    void sort(int[] array) {
        heapify(array);

        int endIdx = array.length - 1;
        while (endIdx > 0) {
            // arr[0] ~ arr[endIdx]，0 总是所有数值中最大的
            // 交换位置，说明 最大值已经在正确的位置上了
            swap(array,0, endIdx);
            // 无序数组长度 -1
            endIdx --;
            // 将剩余的数据重新成堆
            siftDown(array, 0, endIdx);
        }

    }

    private int getLeftChildIndex(int index, int maxIndex) {
        int result = 2 * index + 1;
        if (result > maxIndex) {
            return -1;
        }

        return result;
    }

    private int getRightChildIndex(int index, int maxIndex) {
        int result = 2 * index + 2;
        if (result > maxIndex) {
            return -1;
        }

        return result;
    }

    private int getParentIndex(int index, int maxIndex) {
        if (index < 0 || index > maxIndex) {
            return -1;
        }

        return (index - 1) / 2;
    }

    /**
     * 如果当前值（index）< 子节点，则下沉
     *
     * @param array
     * @param index
     * @param maxIndex
     */
    private void siftDown(int[] array, int index, int maxIndex) {
        int leftChildIndex = getLeftChildIndex(index, maxIndex);
        int rightChildIndex = getRightChildIndex(index, maxIndex);

        if (leftChildIndex != -1 && array[leftChildIndex] > array[index]) {
            swap(array, leftChildIndex, index);
            siftDown(array, leftChildIndex, maxIndex);
        }
        if (rightChildIndex != -1 && array[rightChildIndex] > array[index]) {
            swap(array, rightChildIndex, index);
            siftDown(array, rightChildIndex, maxIndex);
        }
    }

    private void heapify(int[] array) {
        // 从最后一个元素开始，堆化
        int index = array.length - 1;

        while (index >= 0) {
            siftDown(array, index, array.length - 1);
            index --;
        }

    }


}
