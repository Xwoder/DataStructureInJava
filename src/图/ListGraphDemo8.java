package 图;

import java.util.Set;

public class ListGraphDemo8 {
    public static void main(String[] args) {
        Graph<Object, Double> graph = GraphDataTools.undirectedGraph(GraphData.MST_02);
        Set<Graph.EdgeInfo<Object, Double>> set = graph.minimumSpanningTree();

        for (Graph.EdgeInfo<Object, Double> edgeInfo : set) {
             System.out.println(edgeInfo);
        }
    }
}
