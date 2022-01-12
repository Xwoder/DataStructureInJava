package DynamicProgramming.LongestCommonSubstring;

public class LongestCommonSubstring1 {
    public static void main(String[] args) {
        LongestCommonSubstring1 longestCommonSubstring = new LongestCommonSubstring1();
        System.out.println(longestCommonSubstring.lcs("ABCD", "BABC"));
        System.out.println(longestCommonSubstring.lcs("ABCDA", "BABCA"));
    }

    public int lcs(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() <= 0 || str2.length() <= 2) {
            return 0;
        }

        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        int max = 0;
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) != str2.charAt(j - 1)) {
                    continue;
                }

                dp[i][j] = dp[i - 1][j - 1] + 1;
                max = Math.max(dp[i][j], max);

            }
        }

        return max;
    }
}
