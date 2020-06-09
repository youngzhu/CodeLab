package com.youngzy.leetcode.unionfind;

/**
 * 并集查找
 */
public class UnionFind {

    private int[] root;
    private int[] nodeCount; // 每个根上有的节点数

    public UnionFind(int size) {
        root = new int[size];
        nodeCount = new int[size];
        for (int i = 0; i < size; i++) {
            root[i] = i;
            nodeCount[i] = 1;
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
        if (rootX == rootY) {
            return;
        }

        // 将小树 加到 大树下面，显得更平衡
        // 对效率影响不大
        if (nodeCount[rootX] > nodeCount[rootY]) {
            root[rootY] = rootX;
            nodeCount[rootX] += nodeCount[rootY];
        } else {
            root[rootX] = rootY;
            nodeCount[rootY] += nodeCount[rootX];
        }
    }

    public int find(int x) {
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
