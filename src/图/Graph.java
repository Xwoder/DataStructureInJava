package 图;

import java.util.List;
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

    abstract List<V> topologicalSorting();

    public interface WeightManager<W> {
        int compare(W w1, W w2);

        W add(W w1, W w2);

        W zero();
    }

    public interface VertexVisitor<V> {
        void visit(V value);
    }

    class EdgeInfo<V, W> {
        V from;
        V to;
        W weight;

        public EdgeInfo(V from, V to, W weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }


}
