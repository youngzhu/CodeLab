package com.youngzy.stackskills.datastructures.graph;

import java.util.List;

public interface Graph {

    enum GraphType {
        DIRECTED,
        UNDIRECTED
    }

    void addEdge(int v1, int v2);

    List<Integer> getAdjacentVertices(int vertex);

    /**
     * 指向它的边数
     *
     * @param vertex
     * @return
     */
    int getIndegree(int vertex);

    int getNumVertices();

    int getWeightedEdge(int currentVertexId, int neighbour);
}
