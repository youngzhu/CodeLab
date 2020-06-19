package com.youngzy.leetcode.interview;

import java.util.Arrays;
import java.util.Stack;

/**
 * 面试题 17.08. 马戏团人塔
 *
 * 有个马戏团正在设计叠罗汉的表演节目，一个人要站在另一人的肩膀上。
 * 出于实际和美观的考虑，在上面的人要比下面的人矮一点且轻一点。
 * 已知马戏团每个人的身高和体重，请编写代码计算叠罗汉最多能叠几个人。
 *
 * 示例：
 * 输入：height = [65,70,56,75,60,68] weight = [100,150,90,190,95,110]
 * 输出：6
 * 解释：从上往下数，叠罗汉最多能叠 6 层：(56,90), (60,95), (65,100), (68,110), (70,150), (75,190)
 *
 * 提示：height.length == weight.length <= 10000
 *
 * 链接：https://leetcode-cn.com/problems/circus-tower-lcci
 */
public class CircusTower1 {
    /**
     * 用 堆 解决
     *
     * 假想一个最轻的人，在最顶层，作为root
     *
     * 还是失败了。不能用堆或数，要用线性的结构
     *
     * @param height
     * @param weight
     * @return
     */
    public int bestSeqAtIndex(int[] height, int[] weight) {
        // 假想一个最轻的人，身高0，体重 0
        Person fake = new Person(0, 0);

        Person[] arr = new Person[height.length + 1];
        arr[0] = fake;

        for (int i = 0; i < height.length; i++) {
            arr[i + 1] = new Person(height[i], weight[i]);
        }

        // 堆化
        heapify(arr);

        return maxDepth(0, arr.length - 1) - 1;
    }

    public int maxDepth(int root, int maxIdx) {
        if (root == -1) {
            return 0;
        }

        int leftChild = getLeftChildIndex(root, maxIdx);
        int rightChild = getRightChildIndex(root, maxIdx);

        if (leftChild == -1 && rightChild == -1) {
            return 0;
        }

        int leftMaxDepth = 1 + maxDepth(leftChild, maxIdx);
        int rightMaxDepth = 1 + maxDepth(rightChild, maxIdx);

        return Math.max(leftMaxDepth, rightMaxDepth);
    }

    private void heapify(Person[] array) {
        // 从最后一个元素开始，堆化
        int index = array.length - 1;

        while (index >= 0) {
            siftDown(array, index, array.length - 1);
            index --;
        }

    }

    private void siftDown(Person[] array, int index, int maxIndex) {
        int leftChildIndex = getLeftChildIndex(index, maxIndex);
        int rightChildIndex = getRightChildIndex(index, maxIndex);

        // 子节点比父节点小，交换并重新堆化
        if (leftChildIndex != -1 && array[leftChildIndex].compareTo(array[index]) == -1) {
            swap(array, leftChildIndex, index);
            siftDown(array, leftChildIndex, maxIndex);
        }
        if (rightChildIndex != -1 && array[rightChildIndex].compareTo(array[index]) == -1) {
            swap(array, rightChildIndex, index);
            siftDown(array, rightChildIndex, maxIndex);
        }
    }

    private void swap(Person[] array, int i, int j) {
        Person tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
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

    private static class Person implements Comparable<Person> {
        int height, weight;

        public Person(int height, int weight) {
            this.height = height;
            this.weight = weight;
        }

        @Override
        public int compareTo(Person o) {
            if (this.height < o.height && this.weight < o.weight) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
