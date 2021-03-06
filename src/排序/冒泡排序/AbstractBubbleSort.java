package 排序.冒泡排序;

import 排序.Sort;

public abstract class AbstractBubbleSort<E extends Comparable<E>> extends Sort<E> {

    protected String arrayToString(int boundaryIndex) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < elements.length - 1; i++) {
            sb.append(elements[i]).append(i + 1 == boundaryIndex ? " | " : ", ");
        }

        sb.append(elements[elements.length - 1]).append("]");

        return sb.toString();
    }
}
