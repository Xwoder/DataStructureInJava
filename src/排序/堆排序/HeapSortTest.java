package 排序.堆排序;

import Tools.tools.Asserts;
import Tools.tools.Integers;

import java.util.Arrays;

public class HeapSortTest {
    public static void main(String[] args) {
        HeapSort<Integer> heapSort = new HeapSort<>();

        final int size = 10;
        Integer[] elements = Integers.random(size, 100, 999);

        System.out.println(Arrays.toString(elements));

        heapSort.sort(elements);

        System.out.println(Arrays.toString(elements));

        Asserts.test(Integers.isAscOrder(elements));
    }
}
