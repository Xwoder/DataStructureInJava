package 图;

public class ListGraphDemo4 {
    public static void main(String[] args) {
        Graph<Object, Double, Double> graph = GraphDataTools.undirectedGraph(GraphData.DFS_01);
        graph.dfs_recursion(1);
    }
}
