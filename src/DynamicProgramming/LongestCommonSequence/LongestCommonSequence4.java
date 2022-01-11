package DynamicProgramming.LongestCommonSequence;

public class LongestCommonSequence4 {
    public static void main(String[] args) {
        LongestCommonSequence4 lcs = new LongestCommonSequence4();

        System.out.println(lcs.lcs(
                new int[]{1, 3, 5, 9, 10},
                new int[]{1, 4, 9, 19})
        );

        System.out.println(lcs.lcs(
                new int[]{1, 4, 5, 9, 10},
                new int[]{1, 4, 9, 10})
        );

        System.out.println(lcs.lcs(
                new int[]{1, 3, 5},
                new int[]{1, 5, 2})
        );

    }

    private int lcs(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length <= 0
                || nums2 == null || nums2.length <= 0) {
            return 0;
        }

        /*
         * dp[i][j]值为数组 num1 前 i 个元素与数组 nums2 前 j 个元素的最大公共子序列长度
         */
        int[] dp = new int[nums2.length + 1];

        int cur = 0;
        for (int i = 1; i <= nums1.length; i++) {
            cur = 0;
            for (int j = 1; j <= nums2.length; j++) {
                int leftTop = cur;
                cur = dp[j];
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[j] = leftTop + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
            }
        }

        return dp[nums2.length];
    }

}
