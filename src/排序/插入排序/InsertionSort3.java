package 排序.插入排序;

import 排序.Sort;

public class InsertionSort3<E extends Comparable<E>> extends Sort<E> {

    private int insertionIndex(int index) {
        E targetValue = elements[index];
        int targetIndex = -1;

        // 已排序区由 [lowIndex, highIndex) 确定
        int lowIndex = 0;
        int highIndex = index;

        while (lowIndex < highIndex) {
            final int midIndex = (lowIndex + highIndex) >> 1;
            final E midValue = elements[midIndex];
            final int compareResult = compare(targetValue, midValue);
            if (compareResult < 0) {
                highIndex = midIndex;
            } else if (compareResult >= 0) {
                lowIndex = midIndex + 1;
            }

            targetIndex = highIndex;
        }

        return targetIndex;
    }

    protected void sort() {
        for (int i = 1; i < elements.length; i++) {
            final E targetElement = elements[i];
            final int insertionIndex = insertionIndex(i);

            for (int j = i; j > insertionIndex; j--) {
                elements[j] = elements[j - 1];
            }

            elements[insertionIndex] = targetElement;
        }
    }
}
