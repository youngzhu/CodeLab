package com.youngzy.stackskills.algorithms.shortestpath;

import com.youngzy.stackskills.datastructures.graph.Graph;

import java.util.*;

public class Dijkstra {
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

        PriorityQueue<VertexInfo> queue = new PriorityQueue<>(new Comparator<VertexInfo>() {
            @Override
            public int compare(VertexInfo v1, VertexInfo v2) {
                return ((Integer)v1.distance).compareTo(v2.distance);
            }
        });

        Map<Integer, VertexInfo> vertexInfoMap = new HashMap<>();

        result.put(source, new DistanceInfo(0, 0));

        for (int i = 0; i < graph.getNumVertices(); i++) {
            if (i != source) {
                result.put(i, new DistanceInfo());
            }
        }

        VertexInfo sourceVertex = new VertexInfo(source, 0);
        queue.add(sourceVertex);
        vertexInfoMap.put(source, sourceVertex);

        // 广度优先
        while (! queue.isEmpty()) {
            VertexInfo vertexInfo = queue.poll();

            int currentVertexId = vertexInfo.id;

            for (int neighbour : graph.getAdjacentVertices(currentVertexId)) {
                int distance = result.get(currentVertexId).distance
                        + graph.getWeightedEdge(currentVertexId, neighbour);

                // 如果有更短的路径，则更新
                if (result.get(neighbour).distance > distance) {
                    result.get(neighbour).distance = distance;
                    result.get(neighbour).previousVertex = currentVertexId;

                    // 更新队列
                    VertexInfo neighbourVertex = vertexInfoMap.get(neighbour);
                    if (neighbourVertex != null) {
                        queue.remove(neighbourVertex);
                    }
                    neighbourVertex = new VertexInfo(neighbour, distance);
                    queue.add(neighbourVertex);
                    vertexInfoMap.put(neighbour, neighbourVertex);
                }
            }
        }

        return result;
    }

    private static class VertexInfo {
        int id;
        int distance;

        public VertexInfo(int id, int distance) {
            this.id = id;
            this.distance = distance;
        }
    }

    private static class DistanceInfo {
        int distance;
        int previousVertex;

        DistanceInfo(int distance, int previousVertex) {
            this.distance = distance;
            this.previousVertex = previousVertex;
        }

        DistanceInfo() {
            // 有权重的图，有正有负，默认一个极限值
            this.distance = Integer.MAX_VALUE;
            this.previousVertex = -1;
        }
    }
}
