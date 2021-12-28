package 查找;

public class Search<E extends Comparable<E>> {
    private E[] elements;

    protected int compare(int i1, int i2) {
        E e1 = elements[i1];
        E e2 = elements[i2];
        return e1.compareTo(e2);
    }
}
