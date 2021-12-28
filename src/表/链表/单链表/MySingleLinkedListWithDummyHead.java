package 表.链表.单链表;

import 表.链表.MyAbstractList;
import 表.MyList;

/**
 * 链接表
 * 内部带有头结点
 */
public class MySingleLinkedListWithDummyHead<E> extends MyAbstractList<E> {
    private Node<E> first;

    public MySingleLinkedListWithDummyHead() {
        size = 0;
        first = new Node<>(null);
    }

    @Override
    public void add(E element) {
        add(size, element);
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        Node prevNode = (index == 0) ? first : node(index - 1);
        prevNode.next = new Node(element, prevNode.next);

        ++size;
    }

    /**
     * 清空
     */
    @Override
    public void clear() {
        first = null;
        size = 0;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != MyList.ELEMENT_NOT_FOUND;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public int indexOf(E element) {

        Node<E> node = first;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) {
                    return i;
                }
                node = node.next;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) {
                    return i;
                }
                node = node.next;
            }
        }

        return MyList.ELEMENT_NOT_FOUND;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Node<E> node(int index) {

        rangeCheck(index);

        Node node = first.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);

        Node<E> prevNode = index == 0 ? first : node(index - 1);
        Node<E> node = prevNode.next;
        prevNode.next = node.next;

        --size;
        return node.element;
    }

    @Override
    public void remove(E element) {
        remove(indexOf(element));
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E value = node.element;
        node.element = element;
        return value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("size = ").append(size).append(", [");
        for (Node node = first.next; node != null; node = node.next) {
            sb.append(node).append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.element = value;
            this.next = next;
        }

        public Node(E value) {
            this.element = value;
            this.next = null;
        }

        @Override
        public String toString() {
            return "Node(" + element + ')';
        }
    }
}
