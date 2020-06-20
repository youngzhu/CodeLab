package com.youngzy.stackskills.algorithms;

import com.youngzy.stackskills.datastructures.graph.Graph;

import java.util.*;

/**
 * 用来计算最小生成树 Minimum Spanning Tree
 *
 * 两个特点：
 * 1 使用 PriorityQueue 存储边的权重
 * 2 加入新边时不要产生环
 */
public class Kruskal {

    public void spanningTree(Graph graph) {
        PriorityQueue<EdgeInfo> queue = new PriorityQueue<>();

        for (int i = 0; i < graph.getNumVertices(); i++) {
            for (int neighbour : graph.getAdjacentVertices(i)) {
                queue.add(new EdgeInfo(i, neighbour, graph.getWeightedEdge(i, neighbour)));
            }
        }

        Set<Integer> visited = new HashSet<>();
        Set<EdgeInfo> spanningTree = new HashSet<>();
        Map<Integer, Set<Integer>> edgeMap = new HashMap<>();
        for (int v = 0; v < graph.getNumVertices(); v++) {
            edgeMap.put(v, new HashSet<>());
        }

        while (! queue.isEmpty()
                // 最小生成树应该有 v-1 条边
                && spanningTree.size() < graph.getNumVertices() - 1) {
            // 从权重最小的边开始
            EdgeInfo currentEdge = queue.poll();

            edgeMap.get(currentEdge.vertex1).add(currentEdge.vertex2);
            if (hasCycle(edgeMap)) {
                edgeMap.get(currentEdge.vertex1).remove(currentEdge.vertex2);
                continue;
            }

            spanningTree.add(currentEdge);
            visited.add(currentEdge.vertex1);
            visited.add(currentEdge.vertex2);

        }

        if (visited.size() != graph.getNumVertices()) {
            System.out.print("Minimum Spanning Tree is not possible");
        } else {
            System.out.print("Minimum Spanning Tree using Kruskal's Algorithm");
            for (EdgeInfo edge : spanningTree) {
                System.out.print(edge);
            }
        }
    }

    private boolean hasCycle(Map<Integer, Set<Integer>> edgeMap) {
        for (Integer sourceVertex : edgeMap.keySet()) {
            LinkedList<Integer> queue = new LinkedList<>();
            queue.add(sourceVertex);

            Set<Integer> visitedVertices = new HashSet<>();
            while (! queue.isEmpty()) {
                int currentVertex = queue.pollFirst();

                // 如果一个顶点被访问多次，说明有环
                if (visitedVertices.contains(currentVertex)) {
                    return true;
                }

                visitedVertices.add(currentVertex);
                queue.addAll(edgeMap.get(currentVertex));
            }
        }

        return false;
    }

    private static class EdgeInfo implements Comparable<EdgeInfo> {
        int vertex1, vertex2;
        Integer weight;

        public EdgeInfo(int vertex1, int vertex2, Integer weight) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.weight = weight;
        }

        @Override
        public int compareTo(EdgeInfo o) {
            return this.weight.compareTo(o.weight);
        }

        @Override
        public String toString() {
            return String.valueOf(vertex1) +
                    String.valueOf(vertex2);
        }
    }
}
