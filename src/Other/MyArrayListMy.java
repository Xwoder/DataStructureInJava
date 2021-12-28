package Other;

import 表.链表.MyAbstractList;

public class MyArrayListMy<E> extends MyAbstractList<E> {
    /* 默认容量 */
    private static final int DEFAULT_CAPACITY = 10;

    private E[] elements;
    private int size;


    public MyArrayListMy(int capacity) {
        size = 0;
        capacity = Math.max(DEFAULT_CAPACITY, capacity);
        this.elements = (E[]) new Object[capacity];
    }


    public MyArrayListMy() {
        this(DEFAULT_CAPACITY);
    }


    public void add(int index, E element) {
        rangeCheckForAdd(index);

        ensureCapacity(size + 1);

        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }

        elements[index] = element;

        ++size;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * 扩容
     *
     * @param capacity 容量
     */
    private void ensureCapacity(int capacity) {

        int oldCapacity = elements.length;

        if (oldCapacity >= capacity) {
            return;
        }

        final int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }

        elements = newElements;
    }

    public E get(int index) {
        rangeCheck(index);
        return this.elements[index];
    }

    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (this.elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(this.elements[i])) {
                    return i;
                }
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    public E remove(int index) {
        rangeCheck(index);

        E oldElement = elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }

        elements[size--] = null;

        return oldElement;
    }

    @Override
    public void remove(E element) {
        remove(indexOf(element));
    }

    public E set(int index, E element) {
        rangeCheck(index);
        E oldElement = this.elements[index];
        this.elements[index] = element;
        return oldElement;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("size: ").append(size).append("; elements: [");
        for (int i = 0; i < size - 1; i++) {
            sb.append(elements[i]).append(", ");
        }

        sb.append(elements[size - 1]).append("]");
        return sb.toString();
    }

}
