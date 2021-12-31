package 排序.希尔排序;

import 排序.Sort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ShellSort<E extends Comparable<E>> extends Sort<E> {
    private int size;

    private List<Integer> shellStepSequence() {
        List<Integer> stepSequence = new LinkedList<>();
        for (int length = elements.length; length > 1; length >>= 1) {
            stepSequence.add(length / 2);
        }
        System.out.println(Arrays.toString(stepSequence.toArray()));
        return stepSequence;
    }

    @Override
    protected void sort() {
        List<Integer> stepSequence = shellStepSequence();

        for (int step : stepSequence) {
            sort(step);
        }
    }

    private void sort(int step) {
        int rows = elements.length / step;
        for (int col = 0; col < step; col++) {
            // 冒泡排序
            for (int row = 0; row < rows - 1; row++) {
                for (int k = col; k < elements.length - step; k += step) {
                    if (compare(elements[k + step], elements[k]) < 0) {
                        swap(k + step, k);
                    }
                }
            }
        }
    }
}
