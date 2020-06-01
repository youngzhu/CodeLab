package com.youngzy.leetcode.array;

/**
 * 搜索二维矩阵（二）
 * 跟一比较，它是局部有序的
 *
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * 示例:
 *
 * 现有矩阵 matrix 如下：
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 * 给定 target = 20，返回 false。
 *
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii
 */
public class Search2DMatrixII {
    /**
     * 结果：9ms，在末尾的1/3处
     *
     * 1 二分法找到第一列小于等于 目标值 的行
     * 2 在该行上，用二分法查找 目标值
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // 空数组
        if (matrix == null
                || matrix.length == 0
                || matrix[0].length == 0) {
            return false;
        }

        // 数组的行 和 列
        int rows = matrix.length, cols = matrix[0].length;

        // 找出可能在的行 startRow ... endRow
        // matrix[startRow ... endRow][0] <= target
        int maxRowIdx = getMaxRowIdx(matrix, target);

        if (maxRowIdx < 0) {
            return true;
        }

        while (maxRowIdx >= 0) {
            // 如果最后一列 小于目标值，过
            if (matrix[maxRowIdx][cols - 1] == target) {
                return true;
            }

            if (matrix[maxRowIdx][cols - 1] > target) {
                // 使用二分法查找目标值
                if(targeted(matrix[maxRowIdx], target)) {
                    return true;
                }
            }

            maxRowIdx --;
        }

        return false;
    }

    private boolean targeted(int[] matrix, int target) {
        int low = 0, high = matrix.length - 1, mid = 0;

        while (low <= high) {
            mid = low + (high - low) / 2;

            if (matrix[mid] == target
                    || matrix[low] == target
                    || matrix[high] == target
            ) {
                return true;
            }

            if (matrix[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return matrix[mid] == target;
    }

    /**
     *
     * @param matrix
     * @param target
     * @return
     *      最小是 0
     *      负数表示 存在目标值
     */
    private int getMaxRowIdx(int[][] matrix, int target) {
        int low = 0, high = matrix.length - 1, mid = 0;

        while (low <= high) {
            mid = low + (high - low) / 2;

            if (matrix[mid][0] == target
                    || matrix[low][0] == target
                    || matrix[high][0] == target) {
                return -1;
            }

            if (matrix[mid][0] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        // 目标值在 第一行 或 最后一行，都需要处理
        if (matrix[mid][0] < target) {
            return mid;
        }

        return mid == 0 ? 0 : mid - 1;
    }
}
