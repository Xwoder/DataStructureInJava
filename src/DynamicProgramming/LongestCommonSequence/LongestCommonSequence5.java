package DynamicProgramming.LongestCommonSequence;

import Tools.tools.Asserts;

public class LongestCommonSequence5 {
    public static void main(String[] args) {
        LongestCommonSequence5 longestCommonSequence = new LongestCommonSequence5();

        Asserts.test(longestCommonSequence.lcs(new int[]{1, 3, 5, 9, 10}, new int[]{1, 4, 9, 19}) == 2);

        Asserts.test(longestCommonSequence.lcs(new int[]{1, 4, 5, 9, 10}, new int[]{1, 4, 9, 10}) == 4);

        Asserts.test(longestCommonSequence.lcs(new int[]{1, 3, 5}, new int[]{1, 5, 2}) == 2);
    }

    private int lcs(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length <= 0 || nums2 == null || nums2.length <= 0) {
            return 0;
        }

        int[] rowsNums = nums1, colsNums = nums2;
        if (nums1.length < nums2.length) {
            colsNums = nums1;
            rowsNums = nums2;
        }

        int[] dp = new int[colsNums.length + 1];
        for (int i = 1; i <= rowsNums.length; i++) {
            int cur = 0;
            for (int j = 1; j <= colsNums.length; j++) {
                int leftTop = cur;
                cur = dp[j];
                if (rowsNums[i - 1] == colsNums[j - 1]) {
                    dp[j] = leftTop + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
            }
        }
        return dp[colsNums.length];
    }

}
