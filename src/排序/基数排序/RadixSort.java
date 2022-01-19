package 排序.基数排序;

import Tools.tools.Asserts;
import Tools.tools.Integers;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        Integer[] nums = Integers.random(10, 1, 999);

        Integer[] numsCopied = Integers.copy(nums);
        Arrays.sort(numsCopied);

        System.out.println(Arrays.toString(nums));

        RadixSort radixSort = new RadixSort();
        radixSort.sort(nums);

        System.out.println(Arrays.toString(nums));

        Asserts.test(Integers.isAscOrder(nums));
        Asserts.test(Arrays.equals(numsCopied, nums));
    }

    public void sort(Integer[] array) {
        int max = array[0];
        for (Integer num : array) {
            if (num > max) {
                max = num;
            }
        }

        // 利用计数排序实现基数排序
        for (int radix = 1; radix <= max; radix *= 10) {
            countingSort(array, radix);
        }
    }

    private void countingSort(Integer[] array, int radixDivisor) {
        int[] counterArray = new int[10];
        for (int i = 0; i < array.length; i++) {
            ++counterArray[array[i] / radixDivisor % 10];
        }

        for (int i = 1; i < counterArray.length; i++) {
            counterArray[i] += counterArray[i - 1];
        }

        int[] newArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            final Integer element = array[i];
            newArray[--counterArray[element / radixDivisor % 10]] = element;
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = newArray[i];
        }
    }
}
