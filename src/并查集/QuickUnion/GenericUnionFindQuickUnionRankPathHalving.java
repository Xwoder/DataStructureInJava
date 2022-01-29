package 并查集.QuickUnion;

import Tools.tools.Asserts;
import 公共类.Person;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 支持泛型的并查集，基于 Quick Union 的实现，并使用基于 Rank 和 Path Splitting 的优化
 */
public class GenericUnionFindQuickUnionRankPathHalving<V> {

    private Map<V, Node<V>> nodeMap = new HashMap<>();

    public static void main(String[] args) {
        GenericUnionFindQuickUnionRankPathHalving<Person> unionFind = new GenericUnionFindQuickUnionRankPathHalving<>();
        Person p1 = new Person("Jack", 15);
        Person p2 = new Person("Jack", 20);
        Person p3 = new Person("Jack", 25);
        Person p4 = new Person("Jack", 30);

        unionFind.add(p1);
        unionFind.add(p2);
        unionFind.add(p3);
        unionFind.add(p4);

        unionFind.union(p1, p2);
        unionFind.union(p3, p4);
        Asserts.test(unionFind.isSame(p1, p2));
        Asserts.test(unionFind.isSame(p3, p4));
        Asserts.test(!unionFind.isSame(p1, p4));

        unionFind.union(p1,p4);
        Asserts.test(unionFind.isSame(p1, p4));
        Asserts.test(unionFind.isSame(p2, p3));
    }

    public void add(V v) {
        if (nodeMap.containsKey(v)) {
            return;
        }

        nodeMap.put(v, new Node<>(v));
    }

    public boolean isSame(V v1, V v2) {
        return find(v1) == find(v2);
    }

    private Node<V> findRootNode(V v) {
        Node<V> node = nodeMap.get(v);
        if (node == null) {
            return null;
        }

        while (!Objects.equals(node.value, node.parent.value)) {
            node.parent = node.parent.parent;
            node = node.parent;
        }
        return node;
    }

    public V find(V v) {
        Node<V> node = findRootNode(v);
        if (node == null) {
            return null;
        }

        return node.value;
    }

    public void union(V v1, V v2) {
        Node<V> r1 = findRootNode(v1);
        Node<V> r2 = findRootNode(v2);
        if (r1 == null || r2 == null) {
            return;
        }

        if (Objects.equals(r1.value, r2.value)) {
            return;
        }

        if (r1.rank < r2.rank) {
            r1.parent = r2;
        } else if (r1.rank > r2.rank) {
            r2.parent = r1;
        } else {
            r1.parent = r2;
            ++r2.rank;
        }
    }

    private static class Node<V> {
        V value;
        Node<V> parent;
        int rank;

        public Node(V value) {
            this.value = value;
            this.parent = this;
            this.rank = 1;
        }
    }
}
