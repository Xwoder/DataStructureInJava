package 图;

public class ListGraphDemo2 {
    public static void main(String[] args) {
        Graph<Object, Double, Double> graph = GraphDataTools.undirectedGraph(GraphData.BFS_01);
        graph.bfs("A");

        graph.dfs_recursion("A");
    }
}
