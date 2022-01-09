package Greedy.CoinChange;

import java.util.Arrays;

public class CoinChange3 {
    public static void main(String[] args) {
        int money = 19;

        int numberOfCoins = coinsForMoney(money);

        System.out.println(numberOfCoins);
    }


    private static int coinsForMoney(int money) {
        if (money <= 0) {
            return -1;
        }

        Integer[] coinsFaceValue = new Integer[]{1, 5, 20, 25};
        Arrays.sort(coinsFaceValue);

        int[] dp = new int[money + 1];

        for (Integer faceValue : coinsFaceValue) {
            if (faceValue > money) {
                break;
            }
            dp[faceValue] = 1;
        }

        return coinsForMoney(money, dp);
    }

    private static int coinsForMoney(int money, int[] dp) {
        if (money <= 0) {
            return Integer.MAX_VALUE;
        }

        if (dp[money] == 0) {
            int minNumberOfCoins1 = Math.min(coinsForMoney(money - 25, dp), coinsForMoney(money - 20, dp));
            int minNumberOfCoins2 = Math.min(coinsForMoney(money - 5, dp), coinsForMoney(money - 1, dp));
            int numberOfCoins = Math.min(minNumberOfCoins1, minNumberOfCoins2) + 1;
            dp[money] = numberOfCoins;
        }
        return dp[money];
    }
}
