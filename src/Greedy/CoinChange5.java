package Greedy;

import java.util.Arrays;

public class CoinChange5 {
    public static void main(String[] args) {
        System.out.println(coinsForMoney(19));
        System.out.println(coinsForMoney(41));
        System.out.println(coinsForMoney(6));
        System.out.println(coinsForMoney(6));
    }

    private static int coinsForMoney(int money) {
        System.out.println("money: " + money);
        if (money <= 0) {
            return -1;
        }

        int[] dp = new int[money + 1];
        int[] faces = new int[dp.length];

        for (int i = 1; i <= money; i++) {
            int min = Integer.MAX_VALUE;

            if (i >= 1) {
                min = Math.min(min, dp[i - 1]);
                faces[i] = 1;
            }

            if (i >= 5) {
                min = Math.min(min, dp[i - 5]);
                faces[i] = 5;
            }

            if (i >= 20) {
                min = Math.min(min, dp[i - 20]);
                faces[i] = 20;
            }

            if (i >= 25) {
                min = Math.min(min, dp[i - 25]);
                faces[i] = 25;
            }
            dp[i] = min + 1;
        }
        System.out.println("dp: " + Arrays.toString(faces));

        StringBuilder sb = new StringBuilder("coins: ");
        for (int residueMoney = money, face = faces[residueMoney];
             residueMoney > 0;
             residueMoney -= face, face = faces[residueMoney]) {
            sb.append(face).append(' ');
        }
        System.out.println(sb);

        return dp[money];
    }
}
