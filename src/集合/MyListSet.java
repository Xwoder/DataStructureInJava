package 集合;

import java.util.LinkedList;

public class MyListSet<E> implements MySet<E> {

    private LinkedList<E> linkedList;

    public MyListSet() {
        this.linkedList = new LinkedList<>();
    }

    @Override
    public void add(E element) {
        if (linkedList.contains(element)) {
            return;
        }
        linkedList.add(element);
    }

    @Override
    public void clear() {
        linkedList.clear();
    }

    @Override
    public boolean contains(E element) {
        return linkedList.contains(element);
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public void remove(E element) {
        linkedList.remove(element);
    }

    @Override
    public int size() {
        return linkedList.size();
    }

    public void traversal(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        for (E e : linkedList) {
            visitor.visit(e);
        }
    }

}
