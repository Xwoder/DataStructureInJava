package 排序;

public abstract class Sort<E extends Comparable<E>> {

    protected E[] elements;

    protected int compare(int i1, int i2) {
        E e1 = elements[i1];
        E e2 = elements[i2];
        return e1.compareTo(e2);
    }

    protected int compare(E e1, E e2) {
        return e1.compareTo(e2);
    }

    protected abstract void sort();

    public void sort(E[] elements) {
        if (elements == null || elements.length <= 1) {
            return;
        }

        this.elements = elements;

        sort();
    }

    protected final void swap(int i1, int i2) {
        E temp = elements[i1];
        elements[i1] = elements[i2];
        elements[i2] = temp;
    }
}
