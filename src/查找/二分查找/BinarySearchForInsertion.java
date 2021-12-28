package 查找.二分查找;

public class BinarySearchForInsertion {


    public static void main(String[] args) {
        Integer[] elements = new Integer[]{1, 2, 5, 5, 5, 8, 9};
        int targetValue = 6;

        int lowIndex = 0;
        int highIndex = elements.length;
        int targetIndex = -1;

        while (lowIndex < highIndex) {
            final int midIndex = (lowIndex + highIndex) >> 1;
            final int midValue = elements[midIndex];
            if (targetValue < midValue) {
                highIndex = midIndex;
            } else if (targetValue >= midValue) {
                lowIndex = midIndex + 1;
            }

            targetIndex = highIndex;
        }

        System.out.println(targetIndex);
    }
}
