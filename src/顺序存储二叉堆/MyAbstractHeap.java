package 顺序存储二叉堆;

import java.util.Comparator;

public abstract class MyAbstractHeap<E> implements MyHeap<E> {

    protected int size;
    protected Comparator<E> comparator;

    public MyAbstractHeap(Comparator<E> comparator) {
        this.size = 0;
        this.comparator = comparator;
    }

    @Override
    public abstract void add(E element);

    @Override
    public abstract void clear();

    @Override
    public abstract E get();

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public abstract E remove();

    @Override
    public abstract E replace(E element);

    @Override
    public int size() {
        return size;
    }
}
