package 图;

public class ListGraphDemo5 {
    public static void main(String[] args) {
        Graph<Object, Double> graph = GraphDataTools.directedGraph(GraphData.DFS_02);
        graph.dfs_recursion("a", new Graph.VertexVisitor<Object>() {
            @Override
            public void visit(Object value) {
                System.out.println(value);
            }
        });
        graph.dfs_recursion("c", new Graph.VertexVisitor<Object>() {
            @Override
            public void visit(Object value) {
                System.out.println(value);
            }
        });
    }
}
