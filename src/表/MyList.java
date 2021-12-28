package 表;

public interface MyList<E> {
    static final int ELEMENT_NOT_FOUND = -1;

    void add(E element);

    void add(int index, E element);

    void clear();

    boolean contains(E element);

    E get(int index);

    int indexOf(E element);

    boolean isEmpty();

    E remove(int index);

    void remove(E element);

    E set(int index, E element);

    int size();

    String toString();
}
