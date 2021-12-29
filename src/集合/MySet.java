package 集合;

public interface MySet<E> {
    void add(E element);

    void clear();

    boolean contains(E element);

    boolean isEmpty();

    void remove(E element);

    int size();

    void traversal(Visitor<E> visitor);

    public static abstract class Visitor<E> {
        abstract void visit(E element);
    }


}
