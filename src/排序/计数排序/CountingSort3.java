package 排序.计数排序;

import Tools.tools.Asserts;
import Tools.tools.Integers;

import java.util.Arrays;

/*
 * 计数排序
 * <p>
 * 该实现能排序元素取值范围是整数的数组
 *
 * 稳定实现
 */
public class CountingSort3 {
    public static void main(String[] args) {
        CountingSort3 countingSort = new CountingSort3();

        Integer[] nums = Integers.random(10, 100, 999);

        System.out.println(Arrays.toString(nums));

        countingSort.sort(nums);

        System.out.println(Arrays.toString(nums));

        Asserts.test(Integers.isAscOrder(nums));
    }

    private void sort(Integer[] array) {

        int max = array[0];
        int min = array[0];

        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            } else if (array[i] < min) {
                min = array[i];
            }
        }

        int[] counterArray = new int[max - min + 1];
        for (int i = 0; i < array.length; i++) {
            ++counterArray[array[i] - min];
        }

        for (int i = 1; i < counterArray.length; i++) {
            counterArray[i] += counterArray[i - 1];
        }

        int[] newArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            final Integer element = array[i];
            final int index = element - min;
            newArray[--counterArray[index]] = element;
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = newArray[i];
        }
    }
}
