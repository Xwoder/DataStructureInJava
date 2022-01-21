package 图;

public class ListGraphDemo2 {
    public static void main(String[] args) {
        Graph<Object, Double> graph = GraphDataTools.undirectedGraph(GraphData.BFS_01);
        graph.bfs("A", new Graph.VertexVisitor<Object>() {
            @Override
            public void visit(Object value) {
                System.out.println(value);
            }
        });

        graph.dfs_recursion("A", new Graph.VertexVisitor<Object>() {
            @Override
            public void visit(Object value) {
                System.out.println(value);
            }
        });
    }
}
