package 并查集.QuickUnion;

import Tools.tools.Asserts;
import 并查集.UnionFind;

/**
 * 并查集，基于 Quick Union 的实现
 */
public class unionFindQuickUnion extends UnionFind {

    public unionFindQuickUnion(int capacity) {
        super(capacity);
    }

    public static void main(String[] args) {
        unionFindQuickUnion unionFindQuickUnion = new unionFindQuickUnion(12);
        unionFindQuickUnion.union(0, 1);
        unionFindQuickUnion.union(0, 3);
        unionFindQuickUnion.union(0, 4);
        unionFindQuickUnion.union(2, 3);
        unionFindQuickUnion.union(2, 5);
        unionFindQuickUnion.union(6, 7);
        unionFindQuickUnion.union(8, 10);
        unionFindQuickUnion.union(9, 10);
        unionFindQuickUnion.union(9, 11);

        Asserts.test(unionFindQuickUnion.isSame(0, 6) == false);
        Asserts.test(unionFindQuickUnion.isSame(0, 5) == true);

        unionFindQuickUnion.union(4, 6);
        Asserts.test(unionFindQuickUnion.isSame(1, 7) == true);
        Asserts.test(unionFindQuickUnion.isSame(2, 7) == true);
    }

    public void union(int v1, int v2) {
        int r1 = find(v1);
        int r2 = find(v2);
        if (r1 == r2) {
            return;
        }

        parents[r1] = r2;
    }

    public int find(int v) {
        rangeCheck(v);

        while (parents[v] != v) {
            v = parents[v];
        }
        return v;
    }
}
