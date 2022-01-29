package 图;

import 顺序存储二叉堆.MyBinaryHeap;
import 顺序存储二叉堆.MyHeap;

import java.util.*;
import java.util.function.BiConsumer;

public class ListGraph<V, W extends Comparable<W>> extends Graph<V, W> {

    private Map<V, Vertex<V, W>> vertices;
    private Set<Edge<V, W>> edges;

    public ListGraph(WeightManager<W> weightManager) {
        super(weightManager);
        this.vertices = new HashMap<>();
        this.edges = new HashSet<>();
    }

    public ListGraph() {
        this.vertices = new HashMap<>();
        this.edges = new HashSet<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Graph: {").append("\n");
        vertices.forEach(new BiConsumer<V, Vertex<V, W>>() {
            @Override
            public void accept(V v, Vertex<V, W> vertex) {
                sb.append("\t").append(vertex).append("\n");
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
        Vertex<V, W> vertex = this.vertices.remove(v);
        if (vertex == null) {
            return;
        }

        // 删除出边
        for (Iterator<Edge<V, W>> iterator = vertex.outEdges.iterator(); iterator.hasNext(); ) {
            Edge<V, W> edge = iterator.next();
            edge.to.inEdges.remove(edge);
            iterator.remove();
            edges.remove(edge);
        }

        // 删除入边
        for (Iterator<Edge<V, W>> iterator = vertex.inEdges.iterator(); iterator.hasNext(); ) {
            Edge<V, W> edge = iterator.next();
            edge.from.outEdges.remove(edge);
            iterator.remove();
            edges.remove(edge);
        }
    }

    @Override
    public void addEdge(V from, V to, W weight) {
        Vertex<V, W> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            fromVertex = new Vertex<>(from);
            vertices.put(from, fromVertex);
        }

        Vertex<V, W> toVertex = vertices.get(to);
        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertices.put(to, toVertex);
        }

        Edge<V, W> edge = new Edge<>(fromVertex, toVertex);
        edge.weight = weight;
        fromVertex.outEdges.remove(edge);
        toVertex.inEdges.remove(edge);
        this.edges.remove(edge);

        fromVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
        this.edges.add(edge);
    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    @Override
    public void removeEdge(V from, V to) {
        Vertex<V, W> fromVertex = this.vertices.get(from);
        if (fromVertex == null) {
            return;
        }

        Vertex<V, W> toVertex = this.vertices.get(to);
        if (toVertex == null) {
            return;
        }

        Edge<V, W> edge = new Edge<>(fromVertex, toVertex);
        if (edges.remove(edge)) {
            fromVertex.outEdges.remove(edge);
            toVertex.inEdges.remove(edge);
        }
    }

    @Override
    public void bfs(V begin, VertexVisitor<V> visitor) {
        if (visitor == null) {
            return;
        }

        Vertex<V, W> beginVertex = this.vertices.get(begin);
        if (beginVertex == null) {
            return;
        }
        System.out.println("BFS: {");

        Set<Vertex<V, W>> visitedVertexSet = new HashSet<>();

        Queue<Vertex<V, W>> queue = new LinkedList<>();
        queue.add(beginVertex);
        visitedVertexSet.add(beginVertex);

        while (!queue.isEmpty()) {
            Vertex<V, W> vertex = queue.poll();

            visitor.visit(vertex.value);

            for (Edge<V, W> outEdge : vertex.outEdges) {
                final Vertex<V, W> toVertex = outEdge.to;
                if (visitedVertexSet.contains(toVertex)) {
                    continue;
                }
                queue.add(toVertex);
                visitedVertexSet.add(toVertex);
            }
        }
        System.out.println("}");
    }

    @Override
    public void dfs_recursion(V begin, VertexVisitor<V> visitor) {
        if (visitor == null) {
            return;
        }

        Vertex<V, W> vertex = this.vertices.get(begin);
        if (vertex == null) {
            return;
        }

        Set<Vertex<V, W>> visitedVertexSet = new HashSet<>();

        System.out.println("DFS: {");
        dfs_recursion(vertex, visitedVertexSet, visitor);
        System.out.println("}");
    }

    private void dfs_recursion(Vertex<V, W> beginVertex, Set<Vertex<V, W>> visitedVertexSet, VertexVisitor<V> visitor) {
        visitor.visit(beginVertex.value);
        visitedVertexSet.add(beginVertex);

        for (Edge<V, W> outEdge : beginVertex.outEdges) {
            Vertex<V, W> toVertex = outEdge.to;
            if (visitedVertexSet.contains(toVertex)) {
                continue;
            }
            dfs_recursion(toVertex, visitedVertexSet, visitor);
        }
    }

    @Override
    public void dfs_iteration(V begin, VertexVisitor<V> visitor) {
        if (visitor == null) {
            return;
        }

        Vertex<V, W> beginVertex = this.vertices.get(begin);
        if (beginVertex == null) {
            return;
        }

        System.out.println("DFS: {");

        Stack<Vertex<V, W>> stack = new Stack<>();
        Set<Vertex<V, W>> visitedVertexSet = new HashSet<>();

        visitor.visit(beginVertex.value);

        visitedVertexSet.add(beginVertex);
        stack.push(beginVertex);

        while (!stack.isEmpty()) {
            Vertex<V, W> fromVertex = stack.pop();

            for (Edge<V, W> outEdge : fromVertex.outEdges) {
                // 从出边集合中取出一条出边
                Vertex<V, W> toVertex = outEdge.to;
                // 如果这条出边之前已经被遍历过，则跳过，取出下一条出边
                if (visitedVertexSet.contains(toVertex)) {
                    continue;
                }

                // 如果这条出边之前没有被遍历过，则遍历之
                stack.push(fromVertex);
                stack.push(toVertex);

                visitor.visit(toVertex.value);
                visitedVertexSet.add(toVertex);

                // break 语句的作用是确保
                break;
            }
        }

        System.out.println("}");
    }

    @Override
    public Set<EdgeInfo<V, W>> minimumSpanningTree() {
        if (vertices.size() == 0) {
            return null;
        }

        if (new Random().nextInt() % 2 == 0) {
            System.out.println("Prim");
            return minimumSpanningTreeOnPrim();
        } else {
            System.out.println("Kruskal");
            return minimumSpanningTreeOnKruskal();
        }
    }

    public Set<EdgeInfo<V, W>> minimumSpanningTreeOnKruskal() {
        if (vertices.size() == 0) {
            return null;
        }

        MyHeap<Edge<V, W>> edgeHeap = new MyBinaryHeap<>(this.edges, new Comparator<Edge<V, W>>() {
            @Override
            public int compare(Edge<V, W> o1, Edge<V, W> o2) {
                return o2.compareTo(o1);
            }
        });


        return null;
    }

    public Set<EdgeInfo<V, W>> minimumSpanningTreeOnPrim() {
        if (vertices.size() == 0) {
            return null;
        }

        Set<EdgeInfo<V, W>> edgeInfos = new HashSet<>();
        Set<Vertex<V, W>> verticesAdded = new HashSet<>();
        Vertex<V, W> vertex = vertices.values().iterator().next();

        verticesAdded.add(vertex);

        MyHeap<Edge<V, W>> heap = new MyBinaryHeap<>(vertex.outEdges, new Comparator<Edge<V, W>>() {
            @Override
            public int compare(Edge<V, W> o1, Edge<V, W> o2) {
                return o2.compareTo(o1);
            }
        });

        while (!heap.isEmpty() && verticesAdded.size() < verticesSize()) {
            Edge<V, W> edge = heap.remove();
            if (verticesAdded.contains(edge.to)) {
                continue;
            }

            edgeInfos.add(edge.info());
            verticesAdded.add(edge.to);
            heap.addAll(edge.to.outEdges);
        }

        return edgeInfos;
    }


    @Override
    public List<V> topologicalSorting() {
        List<V> list = new ArrayList<>(verticesSize());
        Queue<Vertex<V, W>> queue = new LinkedList<>();
        Map<Vertex<V, W>, Integer> inDegreeMap = new HashMap<>();

        this.vertices.forEach(new BiConsumer<V, Vertex<V, W>>() {
            @Override
            public void accept(V v, Vertex<V, W> vertex) {
                final int inDegree = vertex.inEdges.size();
                if (inDegree == 0) {
                    queue.add(vertex);
                } else {
                    inDegreeMap.put(vertex, inDegree);
                }
            }
        });

        while (!queue.isEmpty()) {
            Vertex<V, W> zeroInDegreeVertex = queue.poll();
            list.add(zeroInDegreeVertex.value);

            for (Edge<V, W> outEdge : zeroInDegreeVertex.outEdges) {
                Vertex<V, W> outVertex = outEdge.to;
                Integer inDegreeOfOutVertex = inDegreeMap.get(outVertex);
                if (inDegreeOfOutVertex == 1) {
                    queue.add(outVertex);
                } else {
                    inDegreeMap.replace(outVertex, inDegreeOfOutVertex - 1);
                }
                inDegreeMap.replace(outVertex, inDegreeMap.get(outVertex));
            }
        }
        return list;
    }


    /* 边类 */
    private static class Edge<V, W extends Comparable<W>> implements Comparable<Edge<V, W>> {
        W weight;
        private Vertex<V, W> from;
        private Vertex<V, W> to;

        public Edge(Vertex<V, W> from, Vertex<V, W> to, W weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public Edge(Vertex<V, W> from, Vertex<V, W> to) {
            this.from = from;
            this.to = to;
        }

        EdgeInfo<V, W> info() {
            return new EdgeInfo<V, W>(from.value, to.value, weight);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Edge<?, ?> edge = (Edge<?, ?>) o;
            return from.equals(edge.from) && to.equals(edge.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }

        @Override
        public int compareTo(Edge<V, W> o) {
            return this.weight.compareTo(o.weight);
        }

        @Override
        public String toString() {
            return "Edge{" + "from=" + from.value + ", to=" + to.value + ", weight=" + weight + '}';
        }
    }

    /* 顶点类 */
    private static class Vertex<V, W extends Comparable<W>> {
        /* 值 */ V value;
        /* 出边 */ Set<Edge<V, W>> inEdges = new HashSet<>();
        /* 入边 */ Set<Edge<V, W>> outEdges = new HashSet<>();

        public Vertex(V value) {
            this.value = value;
        }


        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(value);

            sb.append(", out: [");
            for (Edge<V, W> edge : outEdges) {
                sb.append(edge.from.value).append("->").append(edge.to.value).append("(").append(edge.weight).append(")").append(", ");
            }
            sb.append("], in: [");
            for (Edge<V, W> edge : inEdges) {
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
            Vertex<?, ?> vertex = (Vertex<?, ?>) o;
            return value.equals(vertex.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }
}
