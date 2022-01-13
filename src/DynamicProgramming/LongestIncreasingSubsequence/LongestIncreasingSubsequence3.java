package DynamicProgramming.LongestIncreasingSubsequence;

import Tools.tools.Asserts;

public class LongestIncreasingSubsequence3 {
    public static void main(String[] args) {
        LongestIncreasingSubsequence3 lis = new LongestIncreasingSubsequence3();
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
            int begin = 0;
            int end = size;
            while (begin < end) {
                int mid = (begin + end) >> 1;
                if (num <= top[mid]) {
                    end = mid;
                } else {
                    begin = mid + 1;
                }
            }

            top[end] = num;
            if (end == size) {
                ++size;
            }
        }

        return size;
    }
}
