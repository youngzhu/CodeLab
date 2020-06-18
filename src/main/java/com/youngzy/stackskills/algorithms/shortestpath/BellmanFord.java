package com.youngzy.stackskills.algorithms.shortestpath;

import com.youngzy.stackskills.datastructures.graph.Graph;

import java.util.*;

public class BellmanFord {
    public void shortestPath(Graph graph, int source, int destination) {
        Map<Integer, DistanceInfo> distanceTable = buildDistanceTable(graph, source);

        Stack<Integer> stack = new Stack<>();
        stack.push(destination);

        int previousVertex = distanceTable.get(destination).previousVertex;
        while (previousVertex != -1 && previousVertex != source) {
            stack.push(previousVertex);
            previousVertex = distanceTable.get(previousVertex).previousVertex;
        }

        if (previousVertex == -1) {
            System.out.println("无路可走");
        } else {
            System.out.print("最短路径：" + source);
            while (! stack.isEmpty()) {
                System.out.print("->" + stack.pop());
            }
        }
    }

    private Map<Integer, DistanceInfo> buildDistanceTable(Graph graph, int source) {
        Map<Integer, DistanceInfo> result = new HashMap<>();

        for (int i = 0; i < graph.getNumVertices(); i++) {
            result.put(i, new DistanceInfo());
        }

        result.get(source).distance = 0;
        result.get(source).previousVertex = source;

        LinkedList<Integer> queue = new LinkedList<>();

        // 遍历 V-1 次
        for (int cnt = 0; cnt < graph.getNumVertices() - 1; cnt++) {
            for (int v = 0; v < graph.getNumVertices(); v++) {
                queue.add(v);
            }

            // 每条边遍历一次就够了，做个标记
            Set<String> visitedEdges = new HashSet<>();

            while (! queue.isEmpty()) {
                int v = queue.pollFirst();

                for (int i : graph.getAdjacentVertices(v)) {
                    String edge = String.valueOf(v) + String.valueOf(i);
                    if (visitedEdges.contains(edge)) {
                        continue;
                    }
                    visitedEdges.add(edge);

                    int distance = result.get(v).distance
                            + graph.getWeightedEdge(v, i);

                    // 有更短的路径则更新
                    if (result.get(i).distance > distance) {
                        result.get(i).distance = distance;
                        result.get(i).previousVertex = v;
                    }

                }
            }
        }

        // 校验是否有负循环
        // 再次将所有顶点入队
        for (int v = 0; v < graph.getNumVertices(); v++) {
            queue.add(v);
        }

        // 如果还能找到更短的路径，说明有环
        while (! queue.isEmpty()) {
            int v = queue.pollFirst();

            for (int i : graph.getAdjacentVertices(v)) {
                int distance = result.get(v).distance
                        + graph.getWeightedEdge(v, i);

                // 有更短的路径则更新
                if (result.get(i).distance > distance) {
                    throw new RuntimeException("有负环");
                }

            }
        }


        return result;
    }

    private static class DistanceInfo {
        int distance;
        int previousVertex;

        DistanceInfo(int distance, int previousVertex) {
            this.distance = distance;
            this.previousVertex = previousVertex;
        }

        DistanceInfo() {
            // 约定一个最大值
            // 不要用Integer.MAX，可能导致溢出
            this.distance = 10000;
            this.previousVertex = -1;
        }
    }
}
