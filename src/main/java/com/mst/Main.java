package com.mst;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String inputPath = "input/ass_3_input.json";
        String outputPath = "output/ass_3_output.json";

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            // читаем входные данные
            String inputJson = Files.readString(Paths.get(inputPath));
            InputModel inputData = gson.fromJson(inputJson, InputModel.class);

            List<Map<String, Object>> results = new ArrayList<>();

            // обрабатываем каждый граф
            for (InputModel.GraphModel graphData : inputData.graphs) {

                // создаём список рёбер
                List<Edge> edges = new ArrayList<>();
                for (InputModel.EdgeModel e : graphData.edges) {
                    edges.add(new Edge(e.from, e.to, e.weight));
                }

                Graph graph = new Graph(graphData.nodes, edges);

                // запускаем Prim
                long startPrim = System.nanoTime();
                MSTResult primResult = Prim.findMST(graph);
                double primTime = (System.nanoTime() - startPrim) / 1_000_000.0;

                // запускаем Kruskal
                long startKruskal = System.nanoTime();
                MSTResult kruskalResult = Kruskal.findMST(graph);
                double kruskalTime = (System.nanoTime() - startKruskal) / 1_000_000.0;

                // сохраняем результат
                Map<String, Object> record = new LinkedHashMap<>();
                record.put("graph_id", graphData.id);
                record.put("vertices", graphData.nodes.size());
                record.put("edges", graphData.edges.size());
                record.put("prim_total_cost", primResult.getTotalCost());
                record.put("kruskal_total_cost", kruskalResult.getTotalCost());
                record.put("prim_time_ms", primTime);
                record.put("kruskal_time_ms", kruskalTime);
                record.put("prim_operations", primResult.getOperations());
                record.put("kruskal_operations", kruskalResult.getOperations());

                results.add(record);
            }

            // записываем в JSON
            Files.writeString(Paths.get(outputPath), gson.toJson(results));
            System.out.println("✅ Results saved to: " + outputPath);

            // записываем в CSV
            try (PrintWriter writer = new PrintWriter("output/ass_3_comparison.csv")) {
                writer.println("GraphID,Prim_TotalCost,Kruskal_TotalCost,Prim_Time_ms,Kruskal_Time_ms,Prim_Operations,Kruskal_Operations");

                for (Map<String, Object> r : results) {
                    writer.printf(
                            "%s,%s,%s,%s,%s,%s,%s%n",
                            r.get("graph_id"),
                            r.get("prim_total_cost"),
                            r.get("kruskal_total_cost"),
                            r.get("prim_time_ms"),
                            r.get("kruskal_time_ms"),
                            r.get("prim_operations"),
                            r.get("kruskal_operations")
                    );
                }

                System.out.println("✅ CSV comparison saved to: output/ass_3_comparison.csv");
            } catch (Exception e) {
                System.err.println("❌ Error writing CSV file: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }
}
