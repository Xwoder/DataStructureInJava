package 排序.冒泡排序;

import Tools.tools.Asserts;
import Tools.tools.Integers;

import java.util.Arrays;
import java.util.Random;


public class BubbleSortImp2<E extends Comparable<E>> extends AbstractBubbleSort<E> {

    public static void main(String[] args) {
        BubbleSortImp2<Integer> bubbleSort = new BubbleSortImp2<>();

        final int size = 100;
        Integer[] elements1 = Integers.random(size, 100, 999);

        System.out.println(Arrays.toString(elements1));

        bubbleSort.sort(elements1);

        System.out.println(Arrays.toString(elements1));

        Asserts.test(Integers.isAscOrder(elements1));
    }


    @Override
    protected void sort() {
        for (int i = 0; i < elements.length - 1; i++) {
            boolean swap = false;
            for (int j = 0; j < elements.length - 1 - i; j++) {
                // System.out.println("比较" + elements[j] + ", " + elements[j + 1]);
                if (compare(j + 1, j) < 0) {
                    swap(j + 1, j);
                    swap = true;
                }
            }
            if (!swap) {
                break;
            }

            // System.err.println("第" + i + "轮循环完成：" + arrayToString(elements.length - i - 1));
        }
    }

}
