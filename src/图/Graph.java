package 图;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class Graph<V, W> {

    protected WeightManager<W> weightManager;

    public Graph() {
    }

    public Graph(WeightManager<W> weightManager) {
        this.weightManager = weightManager;
    }

    abstract int edgesSize();

    abstract int verticesSize();

    /**
     * 添加顶点
     *
     * @param v 顶点的值
     */
    abstract void addVertex(V v);

    /**
     * 删除顶点
     *
     * @param v 顶点
     */
    abstract void removeVertex(V v);

    /**
     * 添加边
     *
     * @param from   起点
     * @param to     终点
     * @param weight 权值
     */
    abstract void addEdge(V from, V to, W weight);

    /**
     * 添加边
     *
     * @param from 起点
     * @param to   终点
     */
    abstract void addEdge(V from, V to);

    /**
     * 删除边
     *
     * @param from 起点
     * @param to   终点
     */
    abstract void removeEdge(V from, V to);

    /**
     * 广度优先搜索
     *
     * @param begin 起始结点
     */
    abstract void bfs(V begin, VertexVisitor<V> visitor);

    /**
     * 深度优先搜索
     * <p>
     * 递归实现
     *
     * @param begin 起始结点
     */
    abstract void dfs_recursion(V begin, VertexVisitor<V> visitor);

    /**
     * 深度优先搜索
     * <p>
     * 非递归实现，迭代实现
     *
     * @param begin 起始结点
     */
    abstract void dfs_iteration(V begin, VertexVisitor<V> visitor);

    /**
     * 最小生成树
     */
    abstract Set<EdgeInfo<V, W>> minimumSpanningTree();

    abstract Set<EdgeInfo<V, W>> minimumSpanningTreeOnPrim();

    abstract Set<EdgeInfo<V, W>> minimumSpanningTreeOnKruskal();

    public abstract Map<V, PathInfo<V, W>> shortestPathDijkstra(V v);
    public abstract Map<V, PathInfo<V, W>> shortestPathBellmanFord(V v);
    public abstract Map<V, Map<V, PathInfo<V, W>>> shortestPathFloyd();

    abstract List<V> topologicalSorting();

    public interface WeightManager<W> {
        int compare(W w1, W w2);

        W add(W w1, W w2);

        W zero();
    }

    public interface VertexVisitor<V> {
        void visit(V value);
    }

    public static class PathInfo<V, W> {
        protected W weight;
        private List<EdgeInfo<V, W>> edgeInfoList;

        public PathInfo() {
            edgeInfoList = new LinkedList<>();
        }

        public PathInfo(W weight) {
            this();
            this.weight = weight;

        }

        public List<EdgeInfo<V, W>> getEdgeInfoList() {
            return edgeInfoList;
        }

        public void setEdgeInfoList(List<EdgeInfo<V, W>> edgeInfoList) {
            this.edgeInfoList = edgeInfoList;
        }


        @Override
        public String toString() {
            return "PathInfo{" +
                    "weight=" + weight +
                    ", edgeInfoList=" + edgeInfoList +
                    '}';
        }
    }

    public static class EdgeInfo<V, W> {
        private V from;
        private V to;
        private W weight;

        public EdgeInfo(V from, V to, W weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public V getFrom() {
            return from;
        }

        public V getTo() {
            return to;
        }

        public W getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return "EdgeInfo{" + "from=" + from + ", to=" + to + ", weight=" + weight + '}';
        }


    }
}
