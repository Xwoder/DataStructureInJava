package 排序.归并排序;

import 排序.Sort;

import java.lang.reflect.Array;

public class MergeSort1<E extends Comparable<E>> extends Sort<E> {

    private E[] tempElements;

    private void merge(int begin, int mid, int end) {

        int cur = 0;
        int first = begin;
        int second = mid;
        while (first < mid && second < end) {
            final int compareResult = compare(elements[first], elements[second]);
            if (compareResult >= 0) {
                tempElements[cur++] = elements[second++];
            } else if (compareResult < 0) {
                tempElements[cur++] = elements[first++];
            }
        }

        while (first < mid) {
            tempElements[cur++] = elements[first++];
        }

        while (second < end) {
            tempElements[cur++] = elements[second++];
        }

        for (int i = 0; i < end - begin; i++) {
            elements[begin + i] = tempElements[i];
        }
    }

    @Override
    protected void sort() {
        tempElements = (E[]) Array.newInstance(elements.getClass().getComponentType(), elements.length);
        sort(0, elements.length);
    }


    private void sort(int begin, int end) {
        int length = end - begin;
        if (length < 2) {
            return;
        }

        final int mid = (begin + end) >> 1;
        sort(begin, mid);
        sort(mid, end);

        merge(begin, mid, end);
    }


}
