package 顺序存储二叉堆;

import java.util.Collection;
import java.util.Collections;

public interface MyHeap<E> {
    public void add(E element);
    public void addAll(E[] element);
    public void addAll(Collection<E> element);

    public void clear();

    public boolean isEmpty();

    public E remove();

    public E replace(E element);

    public int size();

    public E get();
}
