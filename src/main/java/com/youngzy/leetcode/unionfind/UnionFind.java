package com.youngzy.leetcode.unionfind;

/**
 * 并集查找
 */
public class UnionFind {

    private int[] root;

    public UnionFind(int size) {
        root = new int[size];
        for (int i = 0; i < size; i++) {
            root[i] = i;
        }
    }

    /**
     * 合并
     *
     * @param x
     * @param y
     */
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        root[rootX] = rootY;
    }

    private int find(int x) {
        while (x != root[x]) {
            root[x] = root[root[x]];
            x = root[x];
        }

        return x;
    }

    public boolean isConnect(int x, int y) {
        return find(x) == find(y);
    }
}
