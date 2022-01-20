package 图;

public class ListGraphDemo3 {
    public static void main(String[] args) {
        Graph<Object, Double, Double> graph = GraphDataTools.directedGraph(GraphData.BFS_02);
        graph.bfs(0, new Graph.VertexVisitor<Object>() {
            @Override
            public void visit(Object value) {
                System.out.println(value);
            }
        });
    }
}
