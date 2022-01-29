package 并查集.QuickUnion;

/**
 * 并查集，基于 Quick Union 的实现，并使用基于 Rank 和 Path Splitting 的优化
 */
public class unionFindQuickUnionRankPathSplitting extends unionFindQuickUnionRank {

    public unionFindQuickUnionRankPathSplitting(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);

        if (parents[v] != v) {
            int parent = parents[v];
            parents[v] = parents[parent];
            v = parent;
        }

        return parents[v];
    }
}
