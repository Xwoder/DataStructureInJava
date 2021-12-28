package 队列.循环双端队列;

import java.lang.reflect.Array;

public class MyCircularDualQueue<E> {

    private final int DEFAULT_CAPACITY = 10;
    private int size;
    private E[] elements;

    private int front;

    public MyCircularDualQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];

        size = 0;
        front = 0;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            int loc = offset(i);
            elements[loc] = null;
        }

        size = 0;
        front = 0;
    }

    /**
     * 从头部出队
     */
    public E deQueueFront() {
        E element = this.elements[front];
        elements[front] = null;
        front = offset(1);
        --size;
        return element;
    }

    public E deQueueRear() {
        int loc = offset(size - 1);
        E element = this.elements[loc];
        elements[loc] = null;
        --size;
        return element;
    }

    public void enQueueFront(E element) {
        ensureCapacity(size + 1);

        int loc = offset(-1);
        elements[loc] = element;
        front = loc;
        ++size;
    }

    /**
     * 从队尾入队
     */
    public void enQueueRear(E element) {
        ensureCapacity(size + 1);

        int loc = offset(size);
        elements[loc] = element;
        ++size;
    }

    /**
     * 扩容
     *
     * @param capacity 容量
     */
    private void ensureCapacity(int capacity) {

        final int oldCapacity = elements.length;

        if (oldCapacity >= capacity) {
            return;
        }

        final int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            newElements[i] = elements[offset(i)];
        }

        elements = newElements;

        front = 0;
    }

    /**
     * 队头元素
     */
    public E front() {
        return elements[front];
    }

    /**
     * 是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    private int offset(int index) {
        return (front + index + elements.length) % elements.length;
    }

    /**
     * 队尾元素
     */
    public E rear() {
        return elements[offset(size - 1)];
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("capacity: ").append(elements.length)
                .append(", size: ").append(size)
                .append(", front: ").append(front)
                .append(", [");
        for (int i = 0; i < elements.length; i++) {
            sb.append(elements[i]).append(", ");
        }
        sb.append(']');
        return sb.toString();
    }

}
