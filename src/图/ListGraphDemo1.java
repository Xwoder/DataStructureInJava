package 图;

public class ListGraphDemo1 {
    public static void main(String[] args) {
        Graph<String, Integer, Integer> graph = new ListGraph<>();
        graph.addEdge("V1", "V0", 9);
        graph.addEdge("V1", "V2", 3);
        graph.addEdge("V2", "V0", 2);
        graph.addEdge("V2", "V3", 5);
        graph.addEdge("V3", "V4", 1);
        graph.addEdge("V0", "V4", 6);
        System.out.println(graph);

        graph.bfs("V1");
    }
}
