package 表.链表.双链表.循环双链表;

import 表.链表.MyAbstractList;

public class MyDualCircularLinkedList<E> extends MyAbstractList<E> {
    private Node<E> first;
    private Node<E> last;

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        Node<E> newNode = new Node<>(element);
        if (size == 0) {
            newNode.prev = newNode;
            newNode.next = newNode;
            first = newNode;
            last = newNode;
        } else {
            if (index == 0) {
                newNode.prev = last;
                newNode.next = first;
                first.prev = newNode;
                first = newNode;
                last.next = newNode;
            } else if (index == size) {
                newNode.prev = last;
                newNode.next = first;
                first.prev = newNode;
                last.next = newNode;
                last = newNode;
            } else {
                Node<E> node = node(index);
                newNode.prev = node.prev;
                newNode.next = node;
                node.prev.next = newNode;
                node.prev = newNode;
            }
        }

        ++size;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        Node<E> node = node(index);
        return node.element;
    }

    @Override
    public int indexOf(E element) {
        Node<E> curNode = first;
        if (element == null) {
            for (int i = 0; i < size; i++, curNode = curNode.next) {
                if (curNode.element == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++, curNode = curNode.next) {
                if (element.equals(curNode.element)) {
                    return i;
                }
            }
        }

        return ELEMENT_NOT_FOUND;
    }

    private Node<E> node(int index) {

        rangeCheck(index);

        Node node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node;
    }

    @Override
    public void remove(E element) {
        remove(indexOf(element));
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);

        Node<E> node = node(index);
        E element = node.element;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            if (index == 0) {
                first.next.prev = last;
                first = first.next;
                last.next = first;
            } else if (index == size - 1) {
                last.prev.next = first;
                last = last.prev;
                first.prev = last;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
        }

        --size;
        return element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E value = node.element;
        node.element = element;
        return value;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("size = ").append(size).append(", [");
        Node<E> curNode = first;
        for (int i = 0; i < size; i++, curNode = curNode.next) {
            sb.append(curNode).append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private static class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;

        public Node(E value, Node<E> prev, Node<E> next) {
            this.element = value;
            this.prev = prev;
            this.next = next;
        }

        public Node(E value) {
            this.element = value;
        }

        @Override
        public String toString() {
            return prev.element + "<-" + element + "->" + next.element;
        }
    }
}
