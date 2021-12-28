package 排序.堆排序;

import 排序.Sort;

public class HeapSort<E extends Comparable<E>> extends Sort<E> {

    private int heapSize;

    private void heapify() {
        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    private void siftDown(int index) {

        int halfSize = heapSize >> 1;
        while (index < halfSize /* 非叶子节点 */) {
            /* 左子节点索引 */
            int leftChildIndex = (index << 1) + 1;
            /* 右子节点索引 */
            int rightChildIndex = leftChildIndex + 1;
            int targetChildIndex = leftChildIndex;
            if (rightChildIndex < heapSize) {
                if (compare(targetChildIndex, rightChildIndex) < 0) {
                    targetChildIndex = rightChildIndex;
                }
            }

            if (compare(index, targetChildIndex) > 0) {
                break;
            }


            swap(index, targetChildIndex);
            index = targetChildIndex;
        }
    }


    protected void sort() {

        heapSize = elements.length;

        heapify();

        while (heapSize > 1) {
            swap(0, heapSize - 1);
            --heapSize;

            siftDown(0);
        }
    }

}
