package 图;

public class ListGraphDemo5 {
    public static void main(String[] args) {
        Graph<Object, Double, Double> graph = GraphDataTools.directedGraph(GraphData.DFS_02);
        graph.dfs_recursion("a");
        graph.dfs_recursion("c");
    }
}
