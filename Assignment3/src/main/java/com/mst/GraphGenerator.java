package com.mst;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GraphGenerator {

    static class Edge {
        String from;
        String to;
        int weight;

        Edge(String from, String to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static class Graph {
        int id;
        List<String> nodes;
        List<Edge> edges;

        Graph(int id, List<String> nodes, List<Edge> edges) {
            this.id = id;
            this.nodes = nodes;
            this.edges = edges;
        }
    }

    static class InputData {
        List<Graph> graphs = new ArrayList<>();
    }

    public static void main(String[] args) {
        Random rand = new Random();
        InputData data = new InputData();

        int id = 1;

        // 5 small graphs (5-30 vertices)
        for (int i = 0; i < 5; i++) {
            data.graphs.add(generateGraph(id++, rand.nextInt(26) + 5, rand));
        }

        // 10 medium graphs (30-300 vertices)
        for (int i = 0; i < 10; i++) {
            data.graphs.add(generateGraph(id++, rand.nextInt(271) + 30, rand));
        }

        // 10 large graphs (300-1000 vertices)
        for (int i = 0; i < 10; i++) {
            data.graphs.add(generateGraph(id++, rand.nextInt(701) + 300, rand));
        }

        // 3 extra large graphs (1000-2000 vertices)
        for (int i = 0; i < 3; i++) {
            data.graphs.add(generateGraph(id++, rand.nextInt(1001) + 1000, rand));
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("input/ass_3_input.json")) {
            gson.toJson(data, writer);
            System.out.println("✅ Generated input/ass_3_input.json successfully!");
        } catch (IOException e) {
            System.err.println("❌ Error writing JSON: " + e.getMessage());
        }
    }

    private static Graph generateGraph(int id, int vertexCount, Random rand) {
        List<String> nodes = new ArrayList<>();
        for (int i = 0; i < vertexCount; i++) {
            nodes.add("V" + i);
        }

        List<Edge> edges = new ArrayList<>();
        // создаем случайные рёбра (каждая вершина соединяется с несколькими другими)
        for (int i = 0; i < vertexCount; i++) {
            int connections = rand.nextInt(3) + 1;
            for (int j = 0; j < connections; j++) {
                int target = rand.nextInt(vertexCount);
                if (target != i) {
                    int weight = rand.nextInt(50) + 1;
                    edges.add(new Edge("V" + i, "V" + target, weight));
                }
            }
        }

        return new Graph(id, nodes, edges);
    }
}
