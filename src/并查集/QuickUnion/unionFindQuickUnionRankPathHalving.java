package 并查集.QuickUnion;

/**
 * 并查集，基于 Quick Union 的实现，并使用基于 Rank 和 Path Halving 的优化
 */
public class unionFindQuickUnionRankPathHalving extends unionFindQuickUnionRank {

    public unionFindQuickUnionRankPathHalving(int capacity) {
        super(capacity);
    }

    @Override
    public int find(int v) {
        rangeCheck(v);

        if (parents[v] != v) {
            int grandParent = parents[parents[v]];
            parents[v] = grandParent;
            v = grandParent;
        }

        return parents[v];
    }
}
