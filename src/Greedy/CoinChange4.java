package Greedy;

import java.util.Arrays;

public class CoinChange4 {
    public static void main(String[] args) {

        System.out.println(coinsForMoney(19));
        System.out.println(coinsForMoney(41));
    }


    private static int coinsForMoney(int money) {
        if (money <= 0) {
            return -1;
        }

        int[] dp = new int[money + 1];

        for (int i = 1; i <= money; i++) {
            int min = dp[i - 1];

            if (i >= 5) {
                min = Math.min(min, dp[i - 5]);
            }

            if (i >= 20) {
                min = Math.min(min, dp[i - 20]);
            }

            if (i >= 25) {
                min = Math.min(min, dp[i - 25]);
            }
            dp[i] = min + 1;
            System.out.println(Arrays.toString(dp));
        }

        return dp[money];
    }


}
