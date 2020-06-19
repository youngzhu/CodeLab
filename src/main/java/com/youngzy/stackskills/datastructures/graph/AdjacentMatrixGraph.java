package com.youngzy.stackskills.datastructures.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdjacentMatrixGraph extends AbstractGraph {
    private int[][] adjacentMatrix;

    public AdjacentMatrixGraph(int numVertices, GraphType type) {
        this.numVertices = numVertices;
        this.type = type;

        adjacentMatrix = new int[numVertices][numVertices];
    }

    @Override
    public void addEdge(int v1, int v2) {
        isValidVertex(v1);
        isValidVertex(v2);

        adjacentMatrix[v1][v2] = 1;
        if (type == GraphType.UNDIRECTED) {
            adjacentMatrix[v2][v1] = 1;
        }

    }

    @Override
    public List<Integer> getAdjacentVertices(int vertex) {
        isValidVertex(vertex);

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            if (adjacentMatrix[vertex][i] == 1) {
                result.add(i);
            }
        }

        Collections.sort(result);

        return result;
    }

    @Override
    public int getWeightedEdge(int currentVertexId, int neighbour) {
        return 0;
    }

}
