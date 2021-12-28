package 栈;

public class MySequentialStack<E> {
    private static final int DEFAULT_SIZE = 10;
    E[] elements;
    private int size;

    /**
     * 构造函数
     */
    public MySequentialStack() {
        size = 0;
        elements = (E[]) new Object[DEFAULT_SIZE];
    }

    /**
     * 弹栈
     *
     * @return 栈顶元素
     */
    public E pop() {
        if (size == 0) {
            throw new StackOverflowError();
        }

        E element = elements[--size];

        return element;
    }

    /**
     * 入栈
     *
     * @param element 待入栈元素
     */
    public void push(E element) {
        if (size == elements.length) {
            throw new StackOverflowError();
        }
        elements[size] = element;
        ++size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size: ").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]).append(", ");
        }
        sb.append(']');
        return sb.toString();
    }

    /**
     * 返回栈定元素
     *
     * @return 栈定元素
     */
    public E top() {
        if (size == 0) {
            throw new StackOverflowError();
        }

        E element = elements[size - 1];
        return element;
    }
}
