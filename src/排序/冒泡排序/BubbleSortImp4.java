package 排序.冒泡排序;

import Tools.tools.Asserts;
import Tools.tools.Integers;

import java.util.Arrays;

public class BubbleSortImp4<E extends Comparable<E>> extends AbstractBubbleSort<E> {

    public static void main(String[] args) {
        BubbleSortImp4<Integer> bubbleSort = new BubbleSortImp4<>();

        final int size = 100;
        Integer[] elements = Integers.random(size, 100, 999);

        System.out.println(Arrays.toString(elements));

        bubbleSort.sort(elements);

        System.out.println(Arrays.toString(elements));

        Asserts.test(Integers.isAscOrder(elements));
    }

    @Override
    protected void sort() {
        int lastSwapIndex = elements.length - 1;
        for (int i = 0; i < lastSwapIndex; ) {
            lastSwapIndex -= 1;
            for (int j = 0; j < elements.length - 1 - i; j++) {
                // System.err.println("比较" + elements[j] + ", " + elements[j + 1]);
                if (compare(j + 1, j) < 0) {
                    swap(j + 1, j);
                    lastSwapIndex = j;
                }
            }

            // System.err.println("第" + i + "轮循环完成：" + arrayToString(elements.length - i - 1));
        }
    }
}
