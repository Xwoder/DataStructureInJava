package 排序.插入排序;

import 排序.Sort;

public class InsertionSort1<E extends Comparable<E>> extends Sort<E> {

    protected void sort() {
        for (int i = 1; i < elements.length; i++) {
            for (int j = i; j > 0; --j) {
                if (compare(j - 1, j) > 0) {
                    swap(j - 1, j);
                }
            }
        }
    }

}
