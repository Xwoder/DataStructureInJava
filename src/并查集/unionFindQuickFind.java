package 并查集;

import Tools.tools.Asserts;

/**
 * 并查集，基于 Quick Find 的实现
 */
public class unionFindQuickFind extends UnionFind {

    public unionFindQuickFind(int capacity) {
        super(capacity);
    }

    public static void main(String[] args) {
        unionFindQuickFind quickFindUnionFind = new unionFindQuickFind(12);
        quickFindUnionFind.union(0, 1);
        quickFindUnionFind.union(0, 3);
        quickFindUnionFind.union(0, 4);
        quickFindUnionFind.union(2, 3);
        quickFindUnionFind.union(2, 5);
        quickFindUnionFind.union(6, 7);
        quickFindUnionFind.union(8, 10);
        quickFindUnionFind.union(9, 10);
        quickFindUnionFind.union(9, 11);

        Asserts.test(quickFindUnionFind.isSame(0, 6) == false);
        Asserts.test(quickFindUnionFind.isSame(0, 5) == true);

        quickFindUnionFind.union(4, 6);
        Asserts.test(quickFindUnionFind.isSame(1, 7) == true);
        Asserts.test(quickFindUnionFind.isSame(2, 7) == true);
    }

    public void union(int v1, int v2) {
        /* v1 的根节点 r1 */
        int r1 = find(v1);
        /* v2 的根节点 r2 */
        int r2 = find(v2);
        
        if (r1 == r2) {
            return;
        }

        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == r1) {
                parents[i] = r2;
            }
        }
    }


    public int find(int v) {
        rangeCheck(v);

        return parents[v];
    }
}
