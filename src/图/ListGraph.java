package 图;

import 并查集.QuickUnion.GenericUnionFindQuickUnionRankPathHalving;
import 顺序存储二叉堆.MyBinaryHeap;
import 顺序存储二叉堆.MyHeap;

import java.util.*;

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
        vertices.forEach((v, vertex) -> sb.append("\t").append(vertex).append("\n"));
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
        if (verticesSize() <= 1) {
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

    /**
     * 最小生成树，基于 Prim 算法实现
     */
    @Override
    public Set<EdgeInfo<V, W>> minimumSpanningTreeOnPrim() {
        if (vertices.size() == 0) {
            return null;
        }

        Set<EdgeInfo<V, W>> edgeInfos = new HashSet<>();
        Set<Vertex<V, W>> verticesAdded = new HashSet<>();
        Vertex<V, W> vertex = vertices.values().iterator().next();

        verticesAdded.add(vertex);

        MyHeap<Edge<V, W>> heap = new MyBinaryHeap<>(vertex.outEdges, Comparator.reverseOrder());

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

    /**
     * 最小生成树，基于 Kruskal 算法实现
     */
    @Override
    public Set<EdgeInfo<V, W>> minimumSpanningTreeOnKruskal() {
        if (vertices.size() <= 1) {
            return null;
        }

        MyHeap<Edge<V, W>> edgeMinHeap = new MyBinaryHeap<>(this.edges, Comparator.reverseOrder());

        GenericUnionFindQuickUnionRankPathHalving<Vertex<V, W>> unionFind = new GenericUnionFindQuickUnionRankPathHalving<>();
        vertices.forEach((v, vertex) -> unionFind.add(vertex));

        Set<EdgeInfo<V, W>> edgeInfos = new HashSet<>();

        while (!edgeMinHeap.isEmpty() && edgeInfos.size() < edgesSize() - 1) {
            Edge<V, W> edge = edgeMinHeap.remove();

            if (unionFind.isSame(edge.from, edge.to)) {
                continue;
            }

            edgeInfos.add(edge.info());
            unionFind.union(edge.from, edge.to);
        }


        return edgeInfos;
    }

    @Override
    public Map<V, PathInfo<V, W>> shortestPathDijkstra(V begin) {
        Vertex<V, W> beginVertex = vertices.get(begin);
        if (beginVertex == null) {
            return null;
        }

        /* 已经被选择的路径 */
        Map<V, PathInfo<V, W>> selectedShortestPathMap = new HashMap<>();
        Map<Vertex<V, W>, PathInfo<V, W>> unselectedShortestPathMap = new HashMap<>();
        unselectedShortestPathMap.put(beginVertex, new PathInfo<>(weightManager.zero()));


        while (!unselectedShortestPathMap.isEmpty()) {
            // 从还在桌面上的小石头选出最短的绳子
            final Map.Entry<Vertex<V, W>, PathInfo<V, W>> minWeightPath = selectMinWeightPath(unselectedShortestPathMap);

            // 最短的绳子连接的石头
            final Vertex<V, W> minWeightPathVertex = minWeightPath.getKey();

            // 将石头提起来，放入到已提起来的石子的map，并保存路径
            selectedShortestPathMap.put(minWeightPathVertex.value, minWeightPath.getValue());

            unselectedShortestPathMap.remove(minWeightPathVertex);

            for (Edge<V, W> minWeightPathVertexOutEdge : minWeightPathVertex.outEdges) {
                Vertex<V, W> endVertex = minWeightPathVertexOutEdge.to;
                if (selectedShortestPathMap.containsKey(endVertex.value) || endVertex.equals(beginVertex)) {
                    break;
                }
                relaxForDijkstra(minWeightPath.getValue(),
                        minWeightPathVertexOutEdge,
                        unselectedShortestPathMap);
            }
        }

        selectedShortestPathMap.remove(begin);
        return selectedShortestPathMap;
    }

    @Override
    public Map<V, PathInfo<V, W>> shortestPathBellmanFord(V begin) {
        Vertex<V, W> beginVertex = vertices.get(begin);
        if (beginVertex == null) {
            return null;
        }

        Map<V, PathInfo<V, W>> selectedPathMap = new HashMap<>();
        selectedPathMap.put(begin, new PathInfo<>(weightManager.zero()));

        int count = vertices.size() - 1;
        for (int i = 0; i < count; i++) { // v - 1 次
            for (Edge<V, W> edge : edges) {
                PathInfo<V, W> fromPathInfo = selectedPathMap.get(edge.from.value);
                if (fromPathInfo == null) {
                    continue;
                }
                relaxForBellmanFord(edge, fromPathInfo, selectedPathMap);
            }
        }

        for (Edge<V, W> edge : edges) {
            PathInfo<V, W> fromPath = selectedPathMap.get(edge.from.value);
            if (fromPath == null) {
                continue;
            }
            if (relaxForBellmanFord(edge, fromPath, selectedPathMap)) {
                System.out.println("有负权环");
                return null;
            }
        }

        selectedPathMap.remove(begin);
        return selectedPathMap;
    }

    private boolean relaxForBellmanFord(Edge<V, W> edge, PathInfo<V, W> fromPathInfo, Map<V, PathInfo<V, W>> selectedPathMap) {
        // 新的可选择的最短路径：beginVertex到edge.from的最短路径 + edge.weight
        W newWeight = weightManager.add(fromPathInfo.weight, edge.weight);
        // 以前的最短路径：beginVertex到edge.to的最短路径
        PathInfo<V, W> oldPath = selectedPathMap.get(edge.to.value);
        if (oldPath != null && weightManager.compare(newWeight, oldPath.weight) >= 0) {
            return false;
        }

        if (oldPath == null) {
            oldPath = new PathInfo<>();
            selectedPathMap.put(edge.to.value, oldPath);
        } else {
            oldPath.getEdgeInfoList().clear();
        }

        oldPath.weight = newWeight;
        oldPath.getEdgeInfoList().addAll(fromPathInfo.getEdgeInfoList());
        oldPath.getEdgeInfoList().add(edge.info());

        return true;
    }

    /**
     * 松弛操作
     */
    private void relaxForDijkstra(PathInfo<V, W> minWeightPathInfo,
                                  Edge<V, W> minWeightPathVertexOutEdge,
                                  Map<Vertex<V, W>, PathInfo<V, W>> unselectedShortestPathMap) {
        W newWeight = weightManager.add(minWeightPathInfo.weight, minWeightPathVertexOutEdge.weight);
        PathInfo<V, W> oldPath = unselectedShortestPathMap.get(minWeightPathVertexOutEdge.to);
        if (oldPath == null) {
            oldPath = new PathInfo<>();
            unselectedShortestPathMap.put(minWeightPathVertexOutEdge.to, oldPath);
        } else {
            W oldWeight = oldPath.weight;
            if (weightManager.compare(newWeight, oldWeight) >= 0) {
                return;
            }

            oldPath.getEdgeInfoList().clear();
        }
        oldPath.weight = newWeight;
        oldPath.getEdgeInfoList().addAll(minWeightPathInfo.getEdgeInfoList());
        oldPath.getEdgeInfoList().add(minWeightPathVertexOutEdge.info());
    }

    private Map.Entry<Vertex<V, W>, PathInfo<V, W>> selectMinWeightPath(Map<Vertex<V, W>, PathInfo<V, W>> pathMap) {
        final Iterator<Map.Entry<Vertex<V, W>, PathInfo<V, W>>> iter = pathMap.entrySet().iterator();
        Map.Entry<Vertex<V, W>, PathInfo<V, W>> minWeightPath = iter.next();

        while (iter.hasNext()) {
            Map.Entry<Vertex<V, W>, PathInfo<V, W>> path = iter.next();
            if (weightManager.compare(path.getValue().weight, minWeightPath.getValue().weight) < 0) {
                minWeightPath = path;
            }
        }

        return minWeightPath;
    }


    @Override
    public List<V> topologicalSorting() {
        List<V> list = new ArrayList<>(verticesSize());
        Queue<Vertex<V, W>> queue = new LinkedList<>();
        Map<Vertex<V, W>, Integer> inDegreeMap = new HashMap<>();

        this.vertices.forEach((v, vertex) -> {
            final int inDegree = vertex.inEdges.size();
            if (inDegree == 0) {
                queue.add(vertex);
            } else {
                inDegreeMap.put(vertex, inDegree);
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
            return new EdgeInfo<>(from.value, to.value, weight);
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
