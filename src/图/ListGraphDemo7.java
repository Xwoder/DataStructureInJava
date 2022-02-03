package 图;

import java.util.Set;

public class ListGraphDemo7 {
    public static void main(String[] args) {
        Graph<Object, Double> graph = GraphDataTools.undirectedGraph(GraphData.MST_01);
        Set<Graph.EdgeInfo<Object, Double>> setForPrim = graph.minimumSpanningTree();

        for (Graph.EdgeInfo<Object, Double> edgeInfo : setForPrim) {
             System.out.println(edgeInfo);
        }
    }
}
