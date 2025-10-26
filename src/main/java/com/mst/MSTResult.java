package com.mst;

import java.util.List;

public class MSTResult {
    private String algorithmName;
    private List<Edge> edges;
    private int totalCost;
    private int operations;
    private double executionTime;

    public MSTResult(String algorithmName, List<Edge> edges, int totalCost, int operations, double executionTime) {
        this.algorithmName = algorithmName;
        this.edges = edges;
        this.totalCost = totalCost;
        this.operations = operations;
        this.executionTime = executionTime;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public int getOperations() {
        return operations;
    }

    public double getExecutionTime() {
        return executionTime;
    }
}
