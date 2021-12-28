package 排序.冒泡排序;

import Tools.tools.Asserts;
import Tools.tools.Integers;

import java.util.Arrays;

public class BubbleSortTest {

    public static void main(String[] args) {
        BubbleSortImp1<Integer> bubbleSortImp1 = new BubbleSortImp1<>();
        BubbleSortImp2<Integer> bubbleSortImp2 = new BubbleSortImp2<>();
        BubbleSortImp3<Integer> bubbleSortImp3 = new BubbleSortImp3<>();
        BubbleSortImp4<Integer> bubbleSortImp4 = new BubbleSortImp4<>();

        final int size = 100;
        Integer[] elements1 = Integers.random(size, 100, 999);
        Integer[] elements2 = Integers.copy(elements1);
        Integer[] elements3 = Integers.copy(elements1);
        Integer[] elements4 = Integers.copy(elements1);

        System.out.println(Arrays.toString(elements1));

        bubbleSortImp1.sort(elements1);
        bubbleSortImp2.sort(elements2);
        bubbleSortImp3.sort(elements3);
        bubbleSortImp4.sort(elements4);

        System.out.println(Arrays.toString(elements1));
        System.out.println(Arrays.toString(elements2));
        System.out.println(Arrays.toString(elements3));
        System.out.println(Arrays.toString(elements4));

        Asserts.test(Integers.isAscOrder(elements1));
        Asserts.test(Integers.isAscOrder(elements2));
        Asserts.test(Integers.isAscOrder(elements3));
        Asserts.test(Integers.isAscOrder(elements4));
    }
}
