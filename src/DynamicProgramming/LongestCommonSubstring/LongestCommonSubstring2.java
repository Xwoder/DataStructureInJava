package DynamicProgramming.LongestCommonSubstring;

public class LongestCommonSubstring2 {
    public static void main(String[] args) {
        LongestCommonSubstring2 longestCommonSubstring = new LongestCommonSubstring2();
        System.out.println(longestCommonSubstring.lcs("ABCD", "BABC"));
        System.out.println(longestCommonSubstring.lcs("ABCDA", "BABCA"));
    }

    public int lcs(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() <= 0 || str2.length() <= 2) {
            return 0;
        }

        String shorterStr = str1, longerStr = str2;
        if (str1.length() < str2.length()) {
            longerStr = str2;
            shorterStr = str1;
        }

        int[] dp = new int[longerStr.length() + 1];
        int max = 0;
        for (int row = 1; row <= shorterStr.length(); row++) {
            for (int col = longerStr.length(); col >= 1; col--) {
                if (str1.charAt(row - 1) != str2.charAt(col - 1)) {
                    dp[col] = 0;
                } else {
                    dp[col] = dp[col - 1] + 1;
                    max = Math.max(dp[col], max);
                }
            }
        }
        return max;
    }
}
