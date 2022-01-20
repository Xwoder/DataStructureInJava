package 图;

public interface Graph<V, E, W> {

    int edgesSize();

    int verticesSize();

    /**
     * 添加顶点
     *
     * @param v 顶点的值
     */
    void addVertex(V v);

    /**
     * 删除顶点
     *
     * @param v 顶点
     */
    void removeVertex(V v);


    /**
     * 添加边
     *
     * @param from   起点
     * @param to     终点
     * @param weight 权值
     */
    void addEdge(V from, V to, W weight);

    /**
     * 删除边
     *
     * @param from 起点
     * @param to   终点
     */
    void removeEdge(V from, V to);


}
