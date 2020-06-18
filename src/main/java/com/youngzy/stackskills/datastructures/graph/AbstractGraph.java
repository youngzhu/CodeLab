package com.youngzy.stackskills.datastructures.graph;

public abstract class AbstractGraph implements  Graph {
    protected GraphType type;
    protected int numVertices;

    public int getNumVertices() {
        return numVertices;
    }


    protected boolean isValidVertex(int vertex) {
        if (vertex >= numVertices || vertex < 0) {
            throw new IllegalArgumentException("Vertex number is not valid");
        }

        return true;
    }

    public int getIndegree(int vertex) {
        isValidVertex(vertex);

        int result = 0;
        for (int i = 0; i < numVertices; i++) {
            if (getAdjacentVertices(i).contains(vertex)) {
                result ++;
            }
        }

        return result;
    }
}
