package 排序.归并排序;

import 排序.Sort;

import java.lang.reflect.Array;

public class MergeSort2<E extends Comparable<E>> extends Sort<E> {

    private E[] leftHalf;

    private void merge(int begin, int mid, int end) {

        for (int i = begin, cur = 0; i < mid; i++, cur++) {
            leftHalf[cur] = elements[i];
        }

        int leftIndex = 0;
        final int leftHalfLength = mid - begin;
        int rightIndex = mid;
        int cur = begin;
        while (leftIndex < leftHalfLength) {
            if (rightIndex < end && compare(leftHalf[leftIndex], elements[rightIndex]) >= 0) {
                elements[cur++] = elements[rightIndex++];
            } else {
                elements[cur++] = leftHalf[leftIndex++];
            }
        }
    }

    @Override
    protected void sort() {
        leftHalf = (E[]) Array.newInstance(elements.getClass().getComponentType(), elements.length >> 1);
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
