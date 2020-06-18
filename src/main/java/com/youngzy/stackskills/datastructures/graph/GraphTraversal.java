package com.youngzy.stackskills.datastructures.graph;

import com.youngzy.stackskills.datastructures.queue.CircularQueue;

import javax.swing.plaf.IconUIResource;
import java.util.List;

public class GraphTraversal {
    public void depthFirstTraversal(Graph graph, int[] visited, int currentVertex) {
        if (visited[currentVertex] == 1) {
            return;
        }
        visited[currentVertex] = 1;

        List<Integer> list = graph.getAdjacentVertices(currentVertex);
        for (int v : list) {
            depthFirstTraversal(graph, visited, v);
        }

        System.out.println(currentVertex + "->");
    }

    public void breadFirstTraversal(Graph graph, int[] visited, int currentVertex) throws CircularQueue.QueueOverflowException, CircularQueue.QueueUnderflowException {
        CircularQueue<Integer> queue = new CircularQueue<>(Integer.class);
        queue.enqueue(currentVertex);

        while (! queue.isEmpty()) {
            int v = queue.dequeue();

            if (visited[v] == 1) {
                continue;
            }

            System.out.println(v + "->");
            visited[v] = 1;

            List<Integer> list = graph.getAdjacentVertices(v);
            for (int adjacentVertex : list) {
                if (visited[adjacentVertex] == 0) {
                    queue.enqueue(adjacentVertex);
                }
            }
        }
    }
}
