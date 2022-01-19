package 排序.计数排序;

import Tools.tools.Integers;

import java.util.Arrays;

/*
 * 计数排序
 * <p>
 * 该实现能排序元素取值范围是整数的数组
 *
 * 非稳定实现
 */
public class CountingSort2 {
    public static void main(String[] args) {
        CountingSort2 countingSort = new CountingSort2();

        Integer[] nums = Integers.random(10, 1, 10);

        System.out.println(Arrays.toString(nums));

        countingSort.sort(nums);

        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
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

        int cur = 0;
        for (int i = 0; i < counterArray.length; i++) {
            int counter = counterArray[i];
            for (int j = 0; j < counter; j++) {
                array[cur++] = i + min;
            }
        }
    }
}
