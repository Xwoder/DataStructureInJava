package 队列;

import java.util.LinkedList;

public class MyQueue<E> {

    private int size;

    private LinkedList<E> elements;

    public MyQueue() {
        this.size = 0;
        this.elements = new LinkedList<>();
    }

    /**
     * 队列非空检查
     */
    private void checkForNotEmpty() {
        if (!isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
    }

    public void clean() {
        this.elements.clear();
    }

    public E deQueue() {
        checkForNotEmpty();
        --size;
        return this.elements.removeFirst();
    }

    public void enQueue(E element) {
        this.elements.add(element);
        ++size;
    }

    public E front() {
        checkForNotEmpty();
        return this.elements.getFirst();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

}
