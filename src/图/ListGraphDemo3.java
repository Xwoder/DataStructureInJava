package 图;

public class ListGraphDemo3 {
    public static void main(String[] args) {
        Graph<Object, Double, Double> graph = GraphDataTools.directedGraph(GraphData.BFS_02);
        graph.bfs(0);
    }
}
