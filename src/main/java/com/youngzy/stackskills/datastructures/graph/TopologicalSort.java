package com.youngzy.stackskills.datastructures.graph;

import java.util.*;

/**
 * 拓扑排序
 */
public class TopologicalSort {
    /**
     * 1 indegree = 0最大（小），之后忽略
     * 2 它原本指向的点的 indegree 相应 -1
     *
     * @param graph
     * @return
     */
    public List<Integer> sort(Graph graph) {
        LinkedList<Integer> queue = new LinkedList<>();

        Map<Integer, Integer> indegreeMap = new HashMap<>();

        for (int v = 0; v < graph.getNumVertices(); v ++) {
            int indegree = graph.getIndegree(v);
            indegreeMap.put(v, indegree);

            if (indegree == 0) {
                queue.add(v);
            }
        }

        List<Integer> sortedList = new ArrayList<>();

        while (! queue.isEmpty()) {
            int vertex = queue.pollLast();
            sortedList.add(vertex);

            List<Integer> adjacentVertices = graph.getAdjacentVertices(vertex);
            for (int adjacentVertex : adjacentVertices) {
                int updatedIndegree = indegreeMap.get(adjacentVertex) - 1;
                indegreeMap.put(adjacentVertex, updatedIndegree);

                if (updatedIndegree == 0) {
                    queue.add(adjacentVertex);
                }
            }
        }

        if (sortedList.size() != graph.getNumVertices()) {
            throw new RuntimeException("图中有环");
        }

        return sortedList;
    }
}
