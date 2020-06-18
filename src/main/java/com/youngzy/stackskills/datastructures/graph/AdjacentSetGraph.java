package com.youngzy.stackskills.datastructures.graph;

import java.util.*;

public class AdjacentSetGraph extends AbstractGraph {
    private List<VertexNode> vertexList;

    public AdjacentSetGraph(int numVertices, GraphType type) {
        this.numVertices = numVertices;
        this.type = type;

        vertexList = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            vertexList.add(new VertexNode(i));
        }
    }

    @Override
    public void addEdge(int v1, int v2) {
        isValidVertex(v1);
        isValidVertex(v2);

        vertexList.get(v1).addEdge(v2);
        if (type == GraphType.UNDIRECTED) {
            vertexList.get(v2).addEdge(v1);
        }

    }

    @Override
    public List<Integer> getAdjacentVertices(int vertex) {
        isValidVertex(vertex);

        return vertexList.get(vertex).getAdjacentVertices();
    }

    static class VertexNode {
        private int vertexId;
        private Set<Integer> adjacentSet;

        public VertexNode(int vertexId) {
            this.vertexId = vertexId;
            adjacentSet = new HashSet<>();
        }

        public int getVertexId() {
            return vertexId;
        }

        public void addEdge(int anotherVertexId) {
            adjacentSet.add(anotherVertexId);
        }

        public List<Integer> getAdjacentVertices() {
            List<Integer> result = new ArrayList<>(adjacentSet);

            Collections.sort(result);

            return result;
        }
    }
}
