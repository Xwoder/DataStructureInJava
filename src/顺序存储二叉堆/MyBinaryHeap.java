package 顺序存储二叉堆;

import Tools.printer.BinaryTreeInfo;
import Tools.printer.BinaryTrees;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class MyBinaryHeap<E> extends MyAbstractHeap<E> implements BinaryTreeInfo<Integer> {

    private static final int DEFAULT_CAPACITY = 10;
    private E[] elements;

    public MyBinaryHeap(Collection<E> elements, Comparator<E> comparator) {
        super(comparator);
        if (elements == null || elements.size() == 0) {
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            final int capacity = Math.max(DEFAULT_CAPACITY, elements.size());
            this.elements = (E[]) new Object[capacity];

            size = elements.size();
            E[] elementArray = (E[]) elements.toArray();
            for (int i = 0; i < elements.size(); i++) {
                this.elements[i] = elementArray[i];
            }

            heapifyFromTopToDown();
        }
    }

    public MyBinaryHeap(Comparator<E> comparator) {
        this((Collection<E>) null, comparator);
    }

    public MyBinaryHeap() {
        this((Collection<E>) null, null);
    }

    public MyBinaryHeap(Collection<E> elements) {
        this(elements, null);
    }

    public MyBinaryHeap(E[] array, Comparator<E> comparator) {
        this(Arrays.asList(array), comparator);
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{68, 72, 43, 50, 38, 10, 90, 65,};

        /* 大根堆 */
        {
            MyBinaryHeap<Integer> maxHeap = new MyBinaryHeap<Integer>(array, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });
            BinaryTrees.println(maxHeap);
        }

        /* 小根堆 */
        {
            MyBinaryHeap<Integer> minHeap = new MyBinaryHeap<Integer>(array, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });
            BinaryTrees.println(minHeap);
        }

    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity(size + 1);

        elements[size] = element;
        ++size;
        siftUp2(size - 1);

    }

    @Override
    public void addAll(E[] element) {
        if (element == null || elements.length <= 0) {
            return;
        }

        for (E e : elements) {
            add(e);
        }
    }

    @Override
    public void addAll(Collection<E> elements) {
        if (elements == null) {
            return;
        }

        for (E element : elements) {
            add(element);
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    private int compare(E e1, E e2) {
        return (comparator != null) ? comparator.compare(e1, e2) : ((Comparable<E>) e1).compareTo(e2);
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("Element must not be null.");
        }
    }

    private void emptyCheck() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Heap is empty.");
        }
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

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    /**
     * 建堆，自下而上的下滤
     */
    public void heapifyFromDownToTop() {
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    /**
     * 建堆，自上而下的上滤
     */
    public void heapifyFromTopToDown() {
        for (int i = 1; i < size; i++) {
            siftUp2(i);
            // BinaryTrees.println(this);
        }
    }

    @Override
    public Integer left(Integer node) {
        Integer myNode = (Integer) node;
        return myNode * 2 + 1 >= size ? null : myNode * 2 + 1;
    }

    @Override
    public E remove() {
        emptyCheck();

        E root = elements[0];
        elements[0] = elements[size - 1];
        elements[size - 1] = null;
        --size;

        siftDown(0);
        return root;
    }


    @Override
    public E replace(E element) {
        elementNotNullCheck(element);

        E root = null;
        if (size == 0) {
            elements[0] = element;
            ++size;
        } else {
            root = elements[0];
            elements[0] = element;
            siftDown(0);
        }

        return root;
    }


    @Override
    public Integer right(Integer node) {
        Integer myNode = (Integer) node;
        return myNode * 2 + 2 >= size ? null : myNode * 2 + 2;
    }

    @Override
    public Integer root() {
        return 0;
    }

    private void siftDown(int index) {

        int halfSize = size >> 1;
        while (index < halfSize /* 非叶子节点 */) {
            /* 左子节点索引 */
            int leftChildIndex = (index << 1) + 1;
            /* 右子节点索引 */
            int rightChildIndex = leftChildIndex + 1;
            int targetChildIndex = leftChildIndex;
            if (rightChildIndex < size) {
                if (compare(elements[targetChildIndex], elements[rightChildIndex]) < 0) {
                    targetChildIndex = rightChildIndex;
                }
            }

            if (compare(elements[index], elements[targetChildIndex]) > 0) {
                break;
            }


            swap(elements, index, targetChildIndex);
            index = targetChildIndex;
        }
    }

    /**
     * 上滤
     * <p>
     * 每次迭代过程中均交换元素
     *
     * @param index 待上滤的元素的索引
     */
    private void siftUp1(int index) {
        for (int curIndex = index, parentIndex = (curIndex - 1) >> 1; (parentIndex >= 0) && (compare(elements[curIndex], elements[parentIndex]) > 0); curIndex = parentIndex, parentIndex = (curIndex - 1) >> 1) {
            swap(elements, curIndex, parentIndex);
        }
    }

    /**
     * 上滤
     * <p>
     * 迭代过程完成后再交换元素
     *
     * @param index 待上滤的元素的索引
     */
    private void siftUp2(int index) {
        if (index == 0) {
            return;
        }

        E element = elements[index];
        int curIndex = index, parentIndex = (curIndex - 1) >> 1;
        for (; (parentIndex >= 0) && (compare(element, elements[parentIndex]) > 0); curIndex = parentIndex, parentIndex = (curIndex - 1) >> 1) {
            elements[curIndex] = elements[parentIndex];
        }
        elements[curIndex] = element;
    }

    @Override
    public Object string(Integer node) {
        Integer index = (Integer) node;
        return elements[index];
    }

    private void swap(E[] elements, int i, int j) {
        E temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("capacity: ").append(elements.length).append(", size: ").append(size).append(", [");
        for (int i = 0; i < elements.length; i++) {
            sb.append(elements[i]).append(", ");
        }
        sb.append(']');
        return sb.toString();
    }

}
