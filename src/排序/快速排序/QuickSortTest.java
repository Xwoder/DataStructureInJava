package 排序.快速排序;

import Tools.tools.Asserts;
import Tools.tools.Integers;

import java.util.Arrays;

public class QuickSortTest {

    public static void main(String[] args) {

        QuickSort1<Integer> quickSort1 = new QuickSort1<>();
        QuickSort2<Integer> quickSort2 = new QuickSort2<>();
        QuickSort3<Integer> quickSort3 = new QuickSort3<>();

        final int size = 100;
        Integer[] elements1 = Integers.random(size, 100, 999);
        Integer[] elements2 = Integers.copy(elements1);
        Integer[] elements3 = Integers.copy(elements1);

        System.out.println(Arrays.toString(elements1));

        quickSort1.sort(elements1);
        quickSort2.sort(elements2);
        quickSort3.sort(elements3);

        System.out.println(Arrays.toString(elements1));

        Asserts.test(Integers.isAscOrder(elements1));
        Asserts.test(Integers.isAscOrder(elements2));
        Asserts.test(Integers.isAscOrder(elements3));
    }
}
