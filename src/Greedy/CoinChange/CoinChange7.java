package Greedy.CoinChange;

/**
 * LeetCode:
 * 322
 * https://leetcode.com/problems/coin-change/
 */
public class CoinChange7 {
    public static void main(String[] args) {

        CoinChange7 coinChange = new CoinChange7();
        System.out.println(coinChange.coinChange(new int[]{1, 2, 5}, 11));
        System.out.println(coinChange.coinChange(new int[]{2}, 3));
        System.out.println(coinChange.coinChange(new int[]{1}, 0));
        System.out.println(coinChange.coinChange(new int[]{1}, 1));
        System.out.println(coinChange.coinChange(new int[]{1}, 2));
    }

    public int coinChange(int[] coins, int amount) {
        if (amount < 0 || coins == null || coins.length <= 0) {
            return -1;
        }

        int[] dp = new int[amount + 1];

        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i < coin) {
                    continue;
                }

                int numberOfCoinsNeededForPrevPick = dp[i - coin];
                if (numberOfCoinsNeededForPrevPick < 0) {
                    continue;
                }

                min = Math.min(min, numberOfCoinsNeededForPrevPick);
            }

            dp[i] = (min == Integer.MAX_VALUE) ? -1 : min + 1;
        }

        return dp[amount];
    }
}
