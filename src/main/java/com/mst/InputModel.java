package com.mst;

import java.util.List;

public class InputModel {
    public List<GraphModel> graphs;

    public static class GraphModel {
        public int id;
        public List<String> nodes;
        public List<EdgeModel> edges;
    }

    public static class EdgeModel {
        public String from;
        public String to;
        public int weight;
    }
}
