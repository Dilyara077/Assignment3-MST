package com.mst;

import java.util.*;

public class Kruskal {
    public static MSTResult findMST(Graph graph) {
        long startTime = System.currentTimeMillis();

        List<Edge> edges = new ArrayList<>(graph.getAllEdges());
        Collections.sort(edges);

        DisjointSet ds = new DisjointSet();
        ds.make(graph.getAllNodes());

        List<Edge> mstEdges = new ArrayList<>();
        int totalCost = 0;
        int operations = 0;

        for (Edge edge : edges) {
            operations++;
            String root1 = ds.find(edge.from);
            String root2 = ds.find(edge.to);

            if (!root1.equals(root2)) {
                mstEdges.add(edge);
                totalCost += edge.weight;
                ds.union(root1, root2);
            }

            if (mstEdges.size() == graph.getAllNodes().size() - 1)
                break;
        }

        long endTime = System.currentTimeMillis();
        double execTime = endTime - startTime;

        return new MSTResult("Kruskal", mstEdges, totalCost, operations, execTime);
    }

    static class DisjointSet {
        private final Map<String, String> parent = new HashMap<>();

        public void make(List<String> nodes) {
            for (String node : nodes) {
                parent.put(node, node);
            }
        }

        public String find(String node) {
            if (!parent.get(node).equals(node)) {
                parent.put(node, find(parent.get(node)));
            }
            return parent.get(node);
        }

        public void union(String node1, String node2) {
            String root1 = find(node1);
            String root2 = find(node2);
            if (!root1.equals(root2)) {
                parent.put(root1, root2);
            }
        }
    }
}
