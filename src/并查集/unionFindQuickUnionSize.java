package 并查集;

/**
 * 并查集，基于 Quick Union 的实现，并使用基于 Size 的优化
 */
public class unionFindQuickUnionSize extends unionFindQuickUnion {

    private int[] sizes;

    public unionFindQuickUnionSize(int capacity) {
        super(capacity);
        sizes = new int[capacity];

        for (int i = 0; i < sizes.length; i++) {
            sizes[i] = 1;
        }
    }

    public void union(int v1, int v2) {
        int r1 = find(v1);
        int r2 = find(v2);
        if (r1 == r2) {
            return;
        }

        if (sizes[r1] < sizes[r2]) {
            parents[r1] = r2;
            sizes[r2] += sizes[r1];
        } else {
            parents[r2] = r1;
            sizes[r1] += sizes[r2];
        }
    }
}
