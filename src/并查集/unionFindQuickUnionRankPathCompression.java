package 并查集;

/**
 * 并查集，基于 Quick Union 的实现，并使用基于 Rank 和 Path Compression 的优化
 */
public class unionFindQuickUnionRankPathCompression extends unionFindQuickUnion {

    private int[] ranks;

    public unionFindQuickUnionRankPathCompression(int capacity) {
        super(capacity);
        ranks = new int[capacity];

        for (int i = 0; i < ranks.length; i++) {
            ranks[i] = 1;
        }
    }

    public void union(int v1, int v2) {
        int r1 = find(v1);
        int r2 = find(v2);
        if (r1 == r2) {
            return;
        }

        if (ranks[r1] < ranks[r2]) {
            parents[r1] = r2;
        } else if (ranks[r1] > ranks[r2]) {
            parents[r2] = r1;
        } else {
            parents[r1] = r2;
            ++ranks[r2];
        }
    }

    @Override
    public int find(int v) {
        rangeCheck(v);

        if (parents[v] != v) {
            parents[v] = find(parents[v]);
        }
        return parents[v];
    }
}
