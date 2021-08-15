package com.youngzy.book.algs4.exercise;

import edu.princeton.cs.algs4.*;

import java.util.Arrays;

/**
 * 修改BinarySearch，使用Counter统计在查找中被检查的键的总数并在查找结束后打印该值。
 *
 * 提示：main中创建一个Counter对象，并将它作为参数传递给rank()
 *
 * @author youngzy
 * @since 2021-07-24
 */
public class E010209 {
    public static int indexOf(int[] a, int key, Counter counter) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            counter.increment();
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

    public static void main(String[] args) {
        In in = new In("algs4/tinyAllowlist.txt");
        int[] whiteList = in.readAllInts();
        Arrays.sort(whiteList);
        Counter counter = new Counter("key");
        while (!StdIn.isEmpty()) {
            // 读取key值，如果不存在于白名单中则打印出来
            int key = StdIn.readInt();
            if (indexOf(whiteList, key, counter) == -1)
                StdOut.println(key);
        }
        StdOut.println(counter);
    }

}
