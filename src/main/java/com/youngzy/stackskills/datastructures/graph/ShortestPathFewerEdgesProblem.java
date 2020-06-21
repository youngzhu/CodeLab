package com.youngzy.stackskills.datastructures.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * 同样的权重，取边更少的路径
 */
public class ShortestPathFewerEdgesProblem {

    public void shortestPath(Graph graph, int source, int destination) {
        Map<Integer, DistanceEdgeInfo> distanceTable = buildDistanceTable(graph, source);

        Stack<Integer> stack = new Stack<>();
        stack.push(destination);

        int previousVertex = distanceTable.get(destination).previousVertex;
        while (previousVertex != -1 && previousVertex != source) {
            stack.push(previousVertex);
            previousVertex = distanceTable.get(previousVertex).previousVertex;
        }

        if (previousVertex == -1) {
            throw new RuntimeException("There's no path from " + source + " to " + destination);
        } else {
            System.out.print("Shortest path is " + source);
            while (! stack.isEmpty()) {
                System.out.print("->" + stack.pop());
            }
        }
    }

    private Map<Integer, DistanceEdgeInfo> buildDistanceTable(Graph graph, int source) {
        Map<Integer, DistanceEdgeInfo> distanceTable = new HashMap<>();

        PriorityQueue<VertexInfo> queue = new PriorityQueue<>();

        for (int i = 0; i < graph.getNumVertices(); i++) {
            distanceTable.put(i, new DistanceEdgeInfo());
        }

        distanceTable.get(source).update(0, 0, source);

        VertexInfo sourceVertex = new VertexInfo(source, 0, 0);
        queue.add(sourceVertex);

        Map<Integer, VertexInfo> vertexInfoMap = new HashMap<>();
        vertexInfoMap.put(source, sourceVertex);

        while (! queue.isEmpty()) {
            VertexInfo currentVertex = queue.poll();
            final DistanceEdgeInfo distanceEdgeInfo = distanceTable.get(currentVertex.id);

            for (int neighbour : graph.getAdjacentVertices(currentVertex.id)) {
                int distance = distanceEdgeInfo.distance
                        + graph.getWeightedEdge(currentVertex.id, neighbour);
                int edges = distanceEdgeInfo.numEdges + 1;

                int neighbourDistance = distanceTable.get(neighbour).distance;
                DistanceEdgeInfo neighbourDisEdgeInfo = distanceTable.get(neighbour);
                boolean shorter = neighbourDistance > distance;
                boolean fewer = neighbourDistance == distance && neighbourDisEdgeInfo.numEdges > edges;
                if (shorter || fewer) {
                    // 以更小的数据，更新邻居
                    distanceTable.get(neighbour).update(distance, edges, currentVertex.id);

                    VertexInfo neighbourVertex = vertexInfoMap.get(neighbour);
                    if (neighbourVertex != null) {
                        queue.remove(neighbourVertex);
                    }

                    // 将崭新的邻居入栈
                    neighbourVertex = new VertexInfo(neighbour, distance, edges);
                    queue.add(neighbourVertex);
                    vertexInfoMap.put(neighbour, neighbourVertex);
                }
            }
        }

        return distanceTable;
    }

    private static class DistanceEdgeInfo {
        int distance;
        int numEdges;
        int previousVertex;

        public DistanceEdgeInfo() {
            this.distance = 9999;
            this.numEdges = 9999;
            this.previousVertex = -1;
        }

        public void update(int distance, int numEdges, int previousVertex) {
            this.distance = distance;
            this.numEdges = numEdges;
            this.previousVertex = previousVertex;
        }
    }

    private static class VertexInfo implements Comparable<VertexInfo> {
        int id;
        Integer distance;
        Integer numEdges;

        public VertexInfo(int id, int distance, int numEdges) {
            this.id = id;
            this.distance = distance;
            this.numEdges = numEdges;
        }

        @Override
        public int compareTo(VertexInfo o) {
            if (this.distance.compareTo(o.distance) != 0) {
                return this.distance.compareTo(o.distance);
            }
            // 如果距离相等，则再按边数排序
            return this.numEdges.compareTo(o.numEdges);
        }
    }
}
