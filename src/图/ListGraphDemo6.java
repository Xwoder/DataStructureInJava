package 图;

import java.util.List;

public class ListGraphDemo6 {
    public static void main(String[] args) {
        Graph<Object, Double> graph = GraphDataTools.directedGraph(GraphData.TOPO);
        List<Object> list = graph.topologicalSorting();
        
        System.out.println(list);
    }
}
