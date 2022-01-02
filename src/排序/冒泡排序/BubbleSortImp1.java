package 排序.冒泡排序;

import Tools.tools.Asserts;
import Tools.tools.Integers;

import java.util.Arrays;


public class BubbleSortImp1<E extends Comparable<E>> extends AbstractBubbleSort<E> {

    public static void main(String[] args) {
        BubbleSortImp1<Integer> bubbleSort = new BubbleSortImp1<>();

        final int size = 100;
        Integer[] elements = Integers.random(size, 100, 999);

        System.out.println(Arrays.toString(elements));

        bubbleSort.sort(elements);

        System.out.println(Arrays.toString(elements));

        Asserts.test(Integers.isAscOrder(elements));
    }


    @Override
    protected void sort() {
        for (int i = 0; i < elements.length - 1; i++) {
            for (int j = 0; j < elements.length - 1 - i; j++) {
                // System.err.println("比较" + elements[j] + ", " + elements[j + 1]);
                if (compare(j + 1, j) < 0) {
                    swap(j + 1, j);
                }
            }

            // System.err.println("第" + i + "轮循环完成：" + arrayToString(elements.length - i - 1));
        }
    }

}
