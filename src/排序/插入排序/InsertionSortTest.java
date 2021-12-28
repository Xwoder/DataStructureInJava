package 排序.插入排序;

import Tools.tools.Asserts;
import Tools.tools.Integers;

import java.util.Arrays;

public class InsertionSortTest {
    public static void main(String[] args) {
        InsertionSort1<Integer> insertionSort1 = new InsertionSort1<>();
        InsertionSort2<Integer> insertionSort2 = new InsertionSort2<>();
        InsertionSort3<Integer> insertionSort3 = new InsertionSort3<>();

        final int size = 100;
        Integer[] elements1 = Integers.random(size, 100, 999);
        Integer[] elements2 = Integers.copy(elements1);
        Integer[] elements3 = Integers.copy(elements1);


        System.out.println("排序前：" + Arrays.toString(elements1));

        insertionSort1.sort(elements1);
        insertionSort2.sort(elements2);
        insertionSort3.sort(elements3);

        System.out.println("排序后：" + Arrays.toString(elements3));

        Asserts.test(Integers.isAscOrder(elements1));
        Asserts.test(Integers.isAscOrder(elements2));
        Asserts.test(Integers.isAscOrder(elements3));

    }
}
