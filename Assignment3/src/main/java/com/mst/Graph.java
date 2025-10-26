package com.mst;

import java.util.*;

public class Graph {
    private List<String> nodes;
    private List<Edge> edges;

    // Конструктор
    public Graph(List<String> nodes, List<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    // Возвращает все вершины
    public List<String> getAllNodes() {
        return nodes;
    }

    // Возвращает все рёбра
    public List<Edge> getAllEdges() {
        return edges;
    }

    // ✅ Новый метод — создаёт список смежности для алгоритма Прима
    public Map<String, List<Edge>> getAdjacencyList() {
        Map<String, List<Edge>> adj = new HashMap<>();

        // Инициализируем пустые списки для каждой вершины
        for (String node : nodes) {
            adj.put(node, new ArrayList<>());
        }

        // Добавляем рёбра (в обе стороны, т.к. граф неориентированный)
        for (Edge e : edges) {
            if (adj.containsKey(e.from)) {
                adj.get(e.from).add(e);
            } else {
                adj.put(e.from, new ArrayList<>(Collections.singletonList(e)));
            }

            // Добавляем обратное ребро
            Edge reverse = new Edge(e.to, e.from, e.weight);
            if (adj.containsKey(e.to)) {
                adj.get(e.to).add(reverse);
            } else {
                adj.put(e.to, new ArrayList<>(Collections.singletonList(reverse)));
            }
        }

        return adj;
    }
}
