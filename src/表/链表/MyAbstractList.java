package 表.链表;

import 表.MyList;

public abstract class MyAbstractList<E> implements MyList<E> {

    protected int size;

    @Override
    public void add(E element) {
        add(size, element);
    }

    public boolean contains(E element) {
        return indexOf(element) != MyList.ELEMENT_NOT_FOUND;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    protected void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
    }

    protected void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
    }

    public int size() {
        return size;
    }


}
