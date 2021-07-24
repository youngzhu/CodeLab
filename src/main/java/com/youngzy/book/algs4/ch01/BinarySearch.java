package com.youngzy.book.algs4.ch01;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * 二分查找
 * @author youngzy
 * @since 2021-07-13
 */
public class BinarySearch {
    // 数组必须是有序的
    public static int indexOf(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // 被查找的值要么不存在，要么必然在a[lo..hi]中
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid])
                hi = mid - 1;
            else if (key > a[mid])
                lo = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    // Execution: java BinarySearch allowlist.txt
    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] whiteList = in.readAllInts();
        Arrays.sort(whiteList);
        while (!StdIn.isEmpty()) {
            // 读取key值，如果不存在于白名单中则打印出来
            int key = StdIn.readInt();
            if (indexOf(whiteList, key) == -1)
                StdOut.println(key);
        }
    }
}
