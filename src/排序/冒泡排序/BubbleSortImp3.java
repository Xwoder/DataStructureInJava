package 排序.冒泡排序;

import Tools.tools.Asserts;
import Tools.tools.Integers;

import java.util.Arrays;
import java.util.Random;


public class BubbleSortImp3<E extends Comparable<E>> extends AbstractBubbleSort<E> {

    public static void main(String[] args) {
        BubbleSortImp3<Integer> bubbleSort = new BubbleSortImp3<>();

        final int size = 100;
        Integer[] elements1 = Integers.random(size, 100, 999);

        System.out.println(Arrays.toString(elements1));

        bubbleSort.sort(elements1);

        System.out.println(Arrays.toString(elements1));

        Asserts.test(Integers.isAscOrder(elements1));
    }


    @Override
    protected void sort() {
        // [0, end) 确定了无序区的范围
        for (int end = elements.length; end > 1; ) {
            /* 最后一次交换的索引 */
            int lastSwap = -1;

            for (int j = 0; j < end - 1; ++j) {
                // System.err.println("比较" + elements[j] + ", " + elements[j + 1]);
                if (compare(j + 1, j) < 0) {
                    swap(j + 1, j);
                    // 记录最后一次交换的索引
                    lastSwap = j + 1;
                }
            }

            // 确定新的无序区边界索引
            end = lastSwap;
        }
    }

}
