package 图;

import java.util.*;
import java.util.function.BiConsumer;

public class ListGraph<V, E, W> implements Graph<V, E, W> {

    private Map<V, Vertex<V, E, W>> vertices;
    private Set<Edge<V, E, W>> edges;

    public ListGraph() {
        this.vertices = new HashMap<>();
        this.edges = new HashSet<>();
    }

    public static void main(String[] args) {
        Graph<String, Integer, Integer> graph = new ListGraph<>();
        graph.addEdge("V1", "V0", 9);
        graph.addEdge("V1", "V2", 3);
        graph.addEdge("V2", "V0", 2);
        graph.addEdge("V2", "V3", 5);
        graph.addEdge("V3", "V4", 1);
        graph.addEdge("V0", "V4", 6);
        System.out.println(graph);

        graph.removeEdge("V0", "V4");
        System.out.println(graph);

        graph.removeVertex("V0");
        System.out.println(graph);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Graph: {").append("\n");
        vertices.forEach(new BiConsumer<V, Vertex<V, E, W>>() {
            @Override
            public void accept(V v, Vertex<V, E, W> vertex) {
                sb.append("    ").append(vertex).append("\n");
            }
        });
        sb.append("}");

        return sb.toString();

    }

    @Override
    public int edgesSize() {
        return edges.size();
    }

    @Override
    public int verticesSize() {
        return vertices.size();
    }

    @Override
    public void addVertex(V value) {
        if (vertices.containsKey(value)) {
            return;
        }

        vertices.put(value, new Vertex<>(value));
    }

    /**
     * 删除顶点
     *
     * @param v 被删除的顶点
     */
    @Override
    public void removeVertex(V v) {
        /* 待删除的顶点 */
        Vertex<V, E, W> vertex = this.vertices.remove(v);
        if (vertex == null) {
            return;
        }

        // 删除出边
        for (Iterator<Edge<V, E, W>> iterator = vertex.outEdges.iterator(); iterator.hasNext(); ) {
            Edge<V, E, W> edge = iterator.next();
            edge.to.inEdges.remove(edge);
            iterator.remove();
            edges.remove(edge);
        }

        // 删除入边
        for (Iterator<Edge<V, E, W>> iterator = vertex.inEdges.iterator(); iterator.hasNext(); ) {
            Edge<V, E, W> edge = iterator.next();
            edge.from.outEdges.remove(edge);
            iterator.remove();
            edges.remove(edge);
        }


    }

    @Override
    public void addEdge(V from, V to, W weight) {
        Vertex<V, E, W> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            fromVertex = new Vertex<>(from);
            vertices.put(from, fromVertex);
        }

        Vertex<V, E, W> toVertex = vertices.get(to);
        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertices.put(to, toVertex);
        }

        Edge<V, E, W> edge = new Edge<>(fromVertex, toVertex);
        edge.weight = weight;
        fromVertex.outEdges.remove(edge);
        toVertex.inEdges.remove(edge);
        this.edges.remove(edge);

        fromVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
        this.edges.add(edge);
    }

    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    @Override
    public void removeEdge(V from, V to) {
        Vertex<V, E, W> fromVertex = this.vertices.get(from);
        if (fromVertex == null) {
            return;
        }

        Vertex<V, E, W> toVertex = this.vertices.get(to);
        if (toVertex == null) {
            return;
        }

        Edge<V, E, W> edge = new Edge<>(fromVertex, toVertex);
        if (edges.remove(edge)) {
            fromVertex.outEdges.remove(edge);
            toVertex.inEdges.remove(edge);
        }
    }


    /* 边类 */
    private static class Edge<V, E, W> {
        W weight;
        private Vertex<V, E, W> from;
        private Vertex<V, E, W> to;

        public Edge(Vertex<V, E, W> from, Vertex<V, E, W> to, W weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public Edge(Vertex<V, E, W> from, Vertex<V, E, W> to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Edge<?, ?, ?> edge = (Edge<?, ?, ?>) o;
            return from.equals(edge.from) && to.equals(edge.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }

    /* 顶点类 */
    private static class Vertex<V, E, W> {
        /* 值 */
        V value;
        /* 出边 */
        Set<Edge<V, E, W>> inEdges = new HashSet<>();
        /* 入边 */
        Set<Edge<V, E, W>> outEdges = new HashSet<>();

        public Vertex(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(value);

            sb.append(", out: [");
            for (Edge<V, E, W> edge : outEdges) {
                sb.append(edge.from.value).append("->").append(edge.to.value).append("(").append(edge.weight).append(")").append(", ");
            }
            sb.append("], in: [");
            for (Edge<V, E, W> edge : inEdges) {
                sb.append(edge.to.value).append("<-").append(edge.from.value).append("(").append(edge.weight).append(")").append(", ");
            }
            sb.append(']');

            return sb.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Vertex<?, ?, ?> vertex = (Vertex<?, ?, ?>) o;
            return value.equals(vertex.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }
}
