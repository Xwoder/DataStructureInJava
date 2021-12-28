package 排序.归并排序;

import Tools.tools.Asserts;
import Tools.tools.Integers;

import java.util.Arrays;

public class MergeSortTest {

    public static void main(String[] args) {
        final int size = 3;
        MergeSort1<Integer> mergeSort1 = new MergeSort1<>();
        MergeSort2<Integer> mergeSort2 = new MergeSort2<>();


        Integer[] elements1 = Integers.random(size, 100, 999);
        elements1 = new Integer[]{903, 169, 665};
        Integer[] elements2 = Integers.copy(elements1);
        System.out.println(Arrays.toString(elements1));

        mergeSort1.sort(elements1);
        mergeSort2.sort(elements2);

        System.out.println(Arrays.toString(elements2));

        Asserts.test(Integers.isAscOrder(elements1));
        Asserts.test(Integers.isAscOrder(elements2));
    }
}
