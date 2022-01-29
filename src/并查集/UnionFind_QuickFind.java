package 并查集;

public class UnionFind_QuickFind extends UnionFind {

    public UnionFind_QuickFind(int capacity) {
        super(capacity);
    }

    public static void main(String[] args) {
        UnionFind_QuickFind quickFindUnionFind = new UnionFind_QuickFind(12);
        quickFindUnionFind.union(0, 1);
        quickFindUnionFind.union(0, 3);
        quickFindUnionFind.union(0, 4);
        quickFindUnionFind.union(2, 3);
        quickFindUnionFind.union(2, 5);
        quickFindUnionFind.union(6, 7);
        quickFindUnionFind.union(8, 10);
        quickFindUnionFind.union(9, 10);
        quickFindUnionFind.union(9, 11);

        System.out.println(quickFindUnionFind.isSame(0, 6));
        System.out.println(quickFindUnionFind.isSame(0, 5));

        quickFindUnionFind.union(4, 6);
        System.out.println(quickFindUnionFind.isSame(1, 7));
        System.out.println(quickFindUnionFind.isSame(2, 7));

    }

    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) {
            return;
        }

        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == p1) {
                parents[i] = p2;
            }
        }
    }


    public int find(int v) {
        rangeCheck(v);

        return parents[v];
    }


}
