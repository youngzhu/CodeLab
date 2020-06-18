package com.youngzy.stackskills.algorithms.shortestpath;

import com.youngzy.stackskills.datastructures.graph.Graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class DistanceTable {
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

        result.put(source, new DistanceInfo(0, 0));

        for (int i = 0; i < graph.getNumVertices(); i++) {
            if (i != source) {
                result.put(i, new DistanceInfo());
            }
        }

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(source);

        // 广度优先
        while (! queue.isEmpty()) {
            int v = queue.pollFirst();

            for (int i : graph.getAdjacentVertices(v)) {
                int distance = result.get(i).distance;

                if (distance == -1) {
                    distance = 1 + result.get(v).distance;

                    result.get(i).distance = distance;
                    result.get(i).previousVertex = v;

                    // 只将有相邻边的顶点加入需要遍历的队列
                    if (! graph.getAdjacentVertices(i).isEmpty()) {
                        queue.add(i);
                    }
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
            this.distance = -1;
            this.previousVertex = -1;
        }
    }
}
