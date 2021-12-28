package 表.链表.双链表;

import 表.链表.MyAbstractList;

public class MyDualLinkedList<E> extends MyAbstractList<E> {
    private Node<E> first;
    private Node<E> last;

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        Node<E> newNode = new Node<>(element);

        if (size == 0) {
            newNode.next = null;
            newNode.prev = null;
            first = newNode;
            last = newNode;
        } else {
            if (index == size) {
                newNode.next = null;
                newNode.prev = last;
                last.next = newNode;
                last = newNode;
            } else if (index == 0) {
                newNode.next = first;
                newNode.prev = null;
                first.prev = newNode;
                first = newNode;
            } else {
                Node<E> node = node(index);
                newNode.next = node;
                newNode.prev = node.prev;
                node.prev.next = newNode;
                node.prev = newNode;
            }
        }

        ++size;
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public int indexOf(E element) {

        int i = 0;
        for (Node<E> curNode = first;
             curNode != null;
             curNode = curNode.next, ++i) {
            if (element == null) {
                if (curNode.element == null) {
                    return i;
                } else if (element.equals(curNode.element)) {
                    return i;
                }
            } else {

            }
        }
        return ELEMENT_NOT_FOUND;
    }

    private Node<E> node(int index) {
        rangeCheck(index);

        Node node = null;
        if (index <= (size >> 1)) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
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
        if (size == 1) {
            first = node.next;
            last = node.prev;
        } else {
            if (index == 0) {
                node.next.prev = null;
                first = node.next;
            } else if (index == size - 1) {
                node.prev.next = node.next;
                last = node.prev;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
        }

        --size;

        return node.element;
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
        for (Node node = first; node != null; node = node.next) {
            sb.append(node).append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private static class Node<E> {
        Node<E> prev;
        E element;
        Node<E> next;

        public Node(E value, Node<E> prev, Node<E> next) {
            this.element = value;
            this.prev = prev;
            this.next = next;
        }

        public Node(E value) {
            this.element = value;
            this.prev = null;
            this.next = null;
        }

        @Override
        public String toString() {
            // return "(" + this.prev.element + "_" + element + "_" + this.next.element + ')';
            StringBuilder sb = new StringBuilder();
            sb.append(this.prev == null ? "null" : this.prev.element);
            sb.append("_(");
            sb.append(element);
            sb.append(")_");
            sb.append(this.next == null ? "null" : this.next.element);
            return sb.toString();
        }
    }
}
