package DivideAndConquer.MaximumSubarray;

public class MaximumSubarray2 {

    public static void main(String[] args) {
        int[] array = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};

        int maxStart = 0;
        int maxEnd = 0;
        int maxSum = array[0];

        // 子数组由元素索引属于 [start, end] 的区间确定
        for (int start = 0; start < array.length; start++) {
            int sum = 0;
            for (int end = start; end < array.length; end++) {
                sum += array[end];
                if (sum > maxSum) {
                    maxStart = start;
                    maxEnd = end;
                    maxSum = sum;
                }
            }
        }

        System.out.println("[" + maxStart + ", " + maxEnd + "], " + maxSum);
    }
}
