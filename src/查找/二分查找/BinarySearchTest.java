package 查找.二分查找;

import Tools.tools.Asserts;
import Tools.tools.Integers;

import java.util.Arrays;
import java.util.Random;

public class BinarySearchTest {
    public static void main(String[] args) {
        BinarySearch1<Integer> binarySearch1 = new BinarySearch1<>();
        BinarySearch2<Integer> binarySearch2 = new BinarySearch2<>();

        int size = 10;
        Integer[] elements = Integers.random(size, 100, 999);
        Arrays.sort(elements);
        System.out.println(Arrays.toString(elements));

        int targetIndex = new Random().nextInt(size);
        int targetValue = elements[targetIndex];
        System.out.println(targetValue);

        int searchResultIndex1 = binarySearch1.search(elements, targetValue);
        int searchResultIndex2 = binarySearch2.search(elements, targetValue);

        Asserts.test(targetIndex == searchResultIndex1);
        Asserts.test(targetIndex == searchResultIndex2);
    }
}
