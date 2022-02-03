package 图;

import java.util.Map;

public class ListGraphDemo9 {
    public static void main(String[] args) {
        Graph<Object, Double> graph = GraphDataTools.undirectedGraph(GraphData.SP);
        final Map<Object, Graph.PathInfo<Object, Double>> shortestPathMap = graph.shortestPathDijkstra("A");

        shortestPathMap.forEach(
                (o, pathInfo) -> System.out.println(o + ", " + pathInfo)
        );
    }
}
