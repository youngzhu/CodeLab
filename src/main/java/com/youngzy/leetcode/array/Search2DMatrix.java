package com.youngzy.leetcode.array;

/**
 * 搜索二维矩阵
 *
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *     每行中的整数从左到右按升序排列。
 *     每行的第一个整数大于前一行的最后一个整数。
 *
 * 示例 1:
 * 输入:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 3
 * 输出: true
 *
 * 示例 2:
 * 输入:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 13
 * 输出: false
 *
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix
 */
public class Search2DMatrix {

    /**
     * 接着完成自己最初的想法
     *
     * 首先找到所在行
     * 再去指定的行搜索
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

        // 比最小的还小
        // 比最大的还大
        if (matrix[0][0] > target
              || matrix[rows - 1][cols - 1] < target) {
            return false;
        }

        // 先用二分法找出所在行
        int rowIdx = getRowIdx(matrix, target);

        if (rowIdx < 0) return true;

        // 在指定的行上（即一维数组）使用二分法，继续找目标值
        int[] rowData = matrix[rowIdx]; // 确定后的一维数组

        int low = 0, high = cols - 1, mid = 0;
        while (low < high) {
            mid = low + (high - low) / 2;

            if (rowData[mid] == target
                    || rowData[low] == target
                    || rowData[high] == target
            ) {
                return true;
            }

            if (rowData[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return rowData[mid] == target;
    }

    /**
     * 找到正确的行数 row
     * （只比较第一列，即 matrix[row][0]）
     *
     * @param matrix
     * @param target
     * @return
     *      -10 代表存在
     */
    private int getRowIdx(int[][] matrix, int target) {
        int low = 0, high = matrix.length - 1, mid = 0;

        int result = high; // 从大值开始，逐步减小

        while (low < high) {
            mid = low + (high - low) / 2;

            if (matrix[result][0] == target
                    || matrix[low][0] == target
                    || matrix[high][0] == target) {
                return -10;
            }

            if (matrix[mid][0] > target) {
                high = mid - 1;

                // 逐步缩小目标值
                result = high;
            } else {
                low = mid + 1;
            }
        }

        // 这是参考来的代码
        // 适用情形是：目标值在最后一行，且不是第一个
        if (matrix[result][0] < target) {
            return result;
        }

        return result == 0 ? 0 : result - 1;
    }

    /**
     * 学习大神的降维打击法： 将二维数组转换成一维的，再使用二分法
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix1(int[][] matrix, int target) {
        // 空数组
        if (matrix == null
                || matrix.length == 0
                || matrix[0].length == 0) {
            return false;
        }

        // 数组的行 和 列
        int rows = matrix.length, cols = matrix[0].length;

        int low = 0;
        int high = rows * cols - 1; // 降维
        int mid = 0;

        while (low < high) {
            mid = low + (high - low) / 2;

            if (getValue(matrix, low) == target
                || getValue(matrix, high) == target
                || getValue(matrix, mid) == target) {
                return true;
            }

            if (getValue(matrix, mid) < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return getValue(matrix, mid) == target;
    }

    private int getValue(int[][] matrix, int idx) {
        // 数组的行 和 列
        int cols = matrix[0].length;
        // 注意这里只用到 列 数
        // 一开始犯错误了
        return matrix[idx / cols][idx % cols]; // 又转为二维
    }

    public boolean searchMatrix0(int[][] matrix, int target) {
        int xIdx = 0; // 横轴
        int yIdx = 0; // 竖轴（只比较第一列，即 matrix[y][0]）

        // 先用二分法找出yIdx
        int value; // 值
        int low = 0, high = matrix.length - 1;

        if (high < 0) {
            // 空的
            return false;
        } else if (high == 0) {
            yIdx = 0;
        } else {
            while (low <= high) {
                yIdx = low + (high - low) / 2;

                if (matrix[yIdx][0] == target) {
                    return true;
                } else if (matrix[yIdx][0] > target) {
                    high = yIdx - 1;
                } else {
                    low = yIdx + 1;
                }
            }

//            yIdx = mid -1;
//
            if (--yIdx < 0) {
                return false;
            }
        }

        // 在横轴上使用二分法
        low = 0;
        high = matrix[0].length - 1;
        xIdx = 0;
        while (low <= high) {
            xIdx = low + (high - low) / 2;

            if (matrix[yIdx][xIdx] == target) {
                return true;
            } else if (matrix[yIdx][xIdx] > target) {
                high = xIdx - 1;
            } else {
                low = xIdx + 1;
            }
        }

        return false;
    }
}
