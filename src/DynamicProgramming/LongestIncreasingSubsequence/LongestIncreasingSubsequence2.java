package DynamicProgramming.LongestIncreasingSubsequence;

import Tools.tools.Asserts;

public class LongestIncreasingSubsequence2 {
    public static void main(String[] args) {
        LongestIncreasingSubsequence2 lis = new LongestIncreasingSubsequence2();
        Asserts.test(lis.lengthOfLIS(new int[]{10, 2, 2, 5, 1, 7, 101, 18}) == 4);
        Asserts.test(lis.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}) == 4);
        Asserts.test(lis.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}) == 4);
        Asserts.test(lis.lengthOfLIS(new int[]{7, 7, 7, 7, 7, 7, 7}) == 1);
    }

    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length < 1) {
            return -1;
        }

        int size = 0;
        int[] top = new int[nums.length];

        for (int num : nums) {
            int j = 0;
            for (; j < size; j++) {
                if (num <= top[j]) {
                    top[j] = num;
                    break;
                }
            }

            if (j == size) {
                top[size++] = num;
            }
        }

        return size;
    }
}
