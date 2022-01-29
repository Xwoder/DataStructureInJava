package 并查集;

/**
 * 并查集，基于 Quick Union 的实现，并使用基于 rank 的优化
 */
public class UnionFind_QuickUnion_Rank extends UnionFind_QuickUnion {

    private int[] ranks;

    public UnionFind_QuickUnion_Rank(int capacity) {
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
}
