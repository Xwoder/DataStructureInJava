package 表.链表.双链表.循环双链表;

import 表.链表.MyAbstractList;

public class MySingleCircularLinkedList<E> extends MyAbstractList<E> {

    private Node<E> first;

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        Node newNode = new Node(element);
        if (size == 0) {
            first = newNode;
            newNode.next = first;
        } else {
            if (index == 0) {
                newNode.next = first;
                Node<E> last = node(size - 1);
                last.next = first;
                first = newNode;
            } else {
                Node<E> prevNode = node(index - 1);
                newNode.next = prevNode.next;
                prevNode.next = newNode;

            }
        }

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
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public int indexOf(E element) {
        Node<E> curNode = first;
        if (element == null) {
            for (int i = 0;
                 i < size;
                 i++, curNode = curNode.next) {
                if (curNode.element == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0;
                 i < size;
                 i++, curNode = curNode.next) {
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
    public E remove(int index) {
        rangeCheck(index);

        Node<E> nodeToRemove = node(index);

        if (size == 1) {
            first = null;
        } else {
            if (index == 0) {
                Node<E> last = node(size - 1);
                last.next = first.next;
                first = first.next;
            } else {
                Node<E> node = node(index - 1);
                node.next = node.next.next;
            }
        }

        --size;
        return nodeToRemove.element;
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
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("size = ").append(size).append(", [");


        Node<E> curNode = first;
        for (int i = 0; i < size; i++) {
            sb.append(curNode).append(", ");
            curNode = curNode.next;
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
            return element + "->" + next.element;
        }
    }
}
