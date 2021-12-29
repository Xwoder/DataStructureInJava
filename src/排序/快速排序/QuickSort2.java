package 排序.快速排序;

import 排序.Sort;

public class QuickSort2<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
        sort(0, elements.length);
    }

    private void sort(int begin, int end) {

        if (end - begin <= 1) {
            return;
        }

        int piovt = begin;
        E piovtElement = elements[piovt];

        int left = begin;
        int right = end - 1;

        while (left < right) {
            while (left < right) {
                if (compare(elements[right], piovtElement) > 0) {
                    --right;
                } else {
                    elements[left++] = elements[right];
                    break;
                }

            }

            while (left < right) {
                if (compare(elements[left], piovtElement) < 0) {
                    ++left;
                } else {
                    elements[right--] = elements[left];
                    break;
                }
            }
        }

        elements[left] = piovtElement;
        sort(begin, left);
        sort(left + 1, end);

    }
}
