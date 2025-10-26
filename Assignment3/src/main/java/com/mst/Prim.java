package com.mst;

import java.util.*;

public class Prim {

    public static MSTResult findMST(Graph graph) {
        long startTime = System.currentTimeMillis();

        Map<String, List<Edge>> adj = graph.getAdjacencyList();
        Set<String> visited = new HashSet<>();
        List<Edge> mstEdges = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        int totalCost = 0;
        int operations = 0;


        String startNode = adj.keySet().iterator().next();
        visited.add(startNode);
        pq.addAll(adj.get(startNode));

        while (!pq.isEmpty() && visited.size() < adj.size()) {
            Edge edge = pq.poll();
            operations++;

            if (visited.contains(edge.to)) continue;

            visited.add(edge.to);
            mstEdges.add(edge);
            totalCost += edge.weight;

            for (Edge next : adj.get(edge.to)) {
                if (!visited.contains(next.to)) {
                    pq.add(next);
                }
            }
        }

        long endTime = System.currentTimeMillis();
        double execTimeMs = endTime - startTime;


        return new MSTResult(
                "Prim",
                mstEdges,
                totalCost,
                operations,
                execTimeMs
        );
    }
}
