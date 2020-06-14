package com.youngzy.stackskills.datastructures.heap;

public class MinimumHeap<T extends Comparable> extends Heap<T> {
    public MinimumHeap(Class<T> clazz) {
        super(clazz);
    }

    public MinimumHeap(Class<T> clazz, int size) {
        super(clazz, size);
    }

    @Override
    protected void siftDown(int idx) {
        int leftIdx = getLeftChildIndex(idx);
        int rightIdx = getRightChildIndex(idx);

        int smallerIdx = -1;
        if (leftIdx != -1 && rightIdx != -1) {
            smallerIdx = getElementAtIndex(leftIdx).compareTo(getElementAtIndex(rightIdx)) < 0
                    ? leftIdx : rightIdx;
        } else if (leftIdx != -1) {
            smallerIdx = leftIdx;
        } else if (rightIdx != -1){
            smallerIdx = rightIdx;
        }

        if (smallerIdx == -1) {
            return;
        }

        // 如果当前节点有比他小的 子节点，则下移
        if (getElementAtIndex(smallerIdx).compareTo(getElementAtIndex(idx)) < 0) {
            swap(smallerIdx, idx);
            siftDown(smallerIdx);
        }

    }

    @Override
    protected void siftUp(int idx) {
        int parentIdx = getParentIndex(idx);

        // 如果当前节点比父节点小，则上移
        if (parentIdx != -1
                && getElementAtIndex(idx).compareTo(getElementAtIndex(parentIdx)) < 0) {
            swap(parentIdx, idx);
            siftUp(parentIdx);
        }

    }
}
