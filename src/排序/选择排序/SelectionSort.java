package 排序.选择排序;

import Tools.tools.Asserts;
import Tools.tools.Integers;
import 排序.Sort;

import java.util.Arrays;
import java.util.Random;

public class SelectionSort extends Sort {
    public static void main(String[] args) {
        Random random = new Random();
        SelectionSort selectionSort = new SelectionSort();

        final int size = 10;
        Integer[] elements1 = Integers.random(size, 100, 999);

        System.out.println("排序前：" + Arrays.toString(elements1));

        selectionSort.sort(elements1);

        System.out.println("排序后：" + Arrays.toString(elements1));

        Asserts.test(Integers.isAscOrder(elements1));
    }

    @Override
    protected void sort() {
        for (int i = 0; i < elements.length - 1; ++i) {
            int minIndex = i;
            for (int j = i + 1; j < elements.length; j++) {
                if (compare(j, minIndex) < 0) {
                    minIndex = j;
                }
            }

            swap(minIndex, i);
        }
    }
}
