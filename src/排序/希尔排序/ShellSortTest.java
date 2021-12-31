package 排序.希尔排序;

import Tools.tools.Asserts;
import Tools.tools.Integers;

import java.util.Arrays;

public class ShellSortTest {
    public static void main(String[] args) {
        ShellSort<Integer> shellSort = new ShellSort<>();
        Integer[] nums = Integers.random(2, 100, 999);
        shellSort.sort(nums);
        System.out.println(Arrays.toString(nums));
        Asserts.test(Integers.isAscOrder(nums));
    }
}
