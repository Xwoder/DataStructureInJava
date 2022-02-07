package 图;

import java.util.Map;
import java.util.function.BiConsumer;

public class ListGraphDemo11 {
    public static void main(String[] args) {
        Graph<Object, Double> graph1 = GraphDataTools.directedGraph(GraphData.SP);
        Map<Object, Map<Object, Graph.PathInfo<Object, Double>>> shortestPathMap1 = graph1.shortestPathFloyd();

        shortestPathMap1.forEach((o, pathInfo) -> {
            System.out.println(o);
            Map<Object, Graph.PathInfo<Object, Double>> to = shortestPathMap1.get(o);
            to.forEach(new BiConsumer<Object, Graph.PathInfo<Object, Double>>() {
                @Override
                public void accept(Object to, Graph.PathInfo<Object, Double> pathInfo) {
                    System.out.println("\tTo: " + to + ", " + pathInfo);
                }
            });
        });

        System.out.println("------");

        Graph<Object, Double> graph2 = GraphDataTools.directedGraph(GraphData.NEGATIVE_WEIGHT1);
        Map<Object, Map<Object, Graph.PathInfo<Object, Double>>> shortestPathMap2 = graph2.shortestPathFloyd();

        shortestPathMap2.forEach((o, pathInfo) -> {
            System.out.println(o);
            Map<Object, Graph.PathInfo<Object, Double>> to = shortestPathMap2.get(o);
            to.forEach(new BiConsumer<Object, Graph.PathInfo<Object, Double>>() {
                @Override
                public void accept(Object to, Graph.PathInfo<Object, Double> pathInfo) {
                    System.out.println("\tTo: " + to + ", " + pathInfo);
                }
            });
        });
    }
}
