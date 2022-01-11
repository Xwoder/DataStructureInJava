package DynamicProgramming.LongestIncreasingSubsequence;

import Tools.tools.Asserts;

public class LongestIncreasingSubsequence1 {
    public static void main(String[] args) {
        LongestIncreasingSubsequence1 lis = new LongestIncreasingSubsequence1();
        Asserts.test(lis.lengthOfLIS(new int[]{10, 2, 2, 5, 1, 7, 101, 18}) == 4);
        Asserts.test(lis.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}) == 4);
        Asserts.test(lis.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}) == 4);
        Asserts.test(lis.lengthOfLIS(new int[]{7, 7, 7, 7, 7, 7, 7}) == 1);
    }

    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length < 1) {
            return -1;
        }

        int[] dp = new int[nums.length];
        int max = dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            int curNum = nums[i];
            for (int j = 0; j < i; j++) {
                if (curNum <= nums[j]) {
                    continue;
                }
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }
}
