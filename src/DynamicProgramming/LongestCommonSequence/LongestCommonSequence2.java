package DynamicProgramming.LongestCommonSequence;

import Tools.tools.Asserts;

public class LongestCommonSequence2 {
    public static void main(String[] args) {
        LongestCommonSequence2 longestCommonSequence = new LongestCommonSequence2();

        Asserts.test(longestCommonSequence.lcs(new int[]{1, 3, 5, 9, 10}, new int[]{1, 4, 9, 19}) == 2);

        Asserts.test(longestCommonSequence.lcs(new int[]{1, 4, 5, 9, 10}, new int[]{1, 4, 9, 10}) == 4);

        Asserts.test(longestCommonSequence.lcs(new int[]{1, 3, 5}, new int[]{1, 5, 2}) == 2);

    }

    private int lcs(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length <= 0
                || nums2 == null || nums2.length <= 0) {
            return 0;
        }

        /*
         * dp[i][j]值为数组 num1 前 i 个元素与数组 nums2 前 j 个元素的最大公共子序列长度
         */
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];

        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[nums1.length][nums2.length];
    }

}
