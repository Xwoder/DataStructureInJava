package 排序.插入排序;

import 排序.Sort;

public class InsertionSort2<E extends Comparable<E>> extends Sort<E> {
    
    protected void sort() {
        for (int i = 1; i < elements.length; i++) {
            E targetElement = elements[i];
            int j = i;
            for (; j > 0 && compare(elements[j - 1], targetElement) > 0; --j) {
                elements[j] = elements[j - 1];
            }

            elements[j] = targetElement;
        }
    }
}
