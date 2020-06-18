package com.youngzy.stackskills.datastructures.heap;

/**
 * heap 可以用来解决的问题
 */
public class HeapProblems {
    /**
     * 从minheap中找到最大值
     *
     * @param minimumHeap
     * @return
     */
    public int getMaximum(MinimumHeap<Integer> minimumHeap) {
        /*
        由于 minheap的特性，最大值一定是在叶子节点中
         */
        int lastIdx = minimumHeap.getCount() - 1;
        int lastParent = minimumHeap.getParentIndex(lastIdx);

        int firstChildIdx = lastParent + 1;

        int max = minimumHeap.getElementAtIndex(firstChildIdx);
        for (int i = firstChildIdx + 1; i <= lastIdx; i++) {
            if (max < minimumHeap.getElementAtIndex(firstChildIdx)) {
                max = minimumHeap.getElementAtIndex(firstChildIdx);
            }
        }

        return max;
    }

    /**
     * 找出最大的K个值
     *
     * @param randomArr
     * @param k
     * @return
     */
    public MinimumHeap findTheKLargest(int[] randomArr, int k) {
        MinimumHeap<Integer> minimumHeap = new MinimumHeap(Integer.class, k);

        /*
        首先用k值将堆填满
        root是最小的值，后面的值，如果小于root则忽略
        如果大于，则移除root，将新值插入堆中
         */
        for (int element : randomArr) {
            if (minimumHeap.isEmpty()) {
                minimumHeap.insert(element);
            } else if (! minimumHeap.isFull()
                    || minimumHeap.getHighestPriority() < element) {
                if (minimumHeap.isFull()) {
                    minimumHeap.removeHighestPriority();
                }

                minimumHeap.insert(element);
            }
        }

        return minimumHeap;
    }
}
