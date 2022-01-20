package 图;

public class ListGraphDemo2 {
    public static void main(String[] args) {
        var graph = GraphDataTools.undirectedGraph(GraphData.BFS_01);
        graph.bfs("A");
    }
}
