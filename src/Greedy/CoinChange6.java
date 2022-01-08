package Greedy;

import java.util.Arrays;

public class CoinChange6 {
    public static void main(String[] args) {
        System.out.println(coinsForMoney(3, new int[]{2}));
        System.out.println(coinsForMoney(11, new int[]{1, 2, 5}));
        System.out.println(coinsForMoney(0, new int[]{1}));
        System.out.println(coinsForMoney(1, new int[]{1}));
        System.out.println(coinsForMoney(1, new int[]{2}));
    }

    public static int coinsForMoney(int money, int[] coinFaceValueArray) {
        System.out.println("money: " + money);
        System.out.println("coins:" + Arrays.toString(coinFaceValueArray));
        if (money <= 0) {
            return -1;
        }

        int[] dp = new int[money + 1];
        int[] faces = new int[dp.length];

        for (int i = 1; i <= money; i++) {
            int min = Integer.MAX_VALUE;
            for (int coinFaceValue : coinFaceValueArray) {
                if (i < coinFaceValue) {
                    continue;
                }

                int coinsNeeded = dp[i - coinFaceValue];
                if (coinsNeeded < 0) {
                    continue;
                }

                min = Math.min(min, coinsNeeded);
                faces[i] = coinFaceValue;
            }

            if (min == Integer.MAX_VALUE) {
                dp[i] = -1;
            } else {
                dp[i] = min + 1;
            }

        }
        System.out.println("dp: " + Arrays.toString(faces));

        int lastCoin = dp[money];
        if (lastCoin > 0) {
            StringBuilder sb = new StringBuilder("coins: ");
            for (int residueMoney = money, face = faces[residueMoney];
                 residueMoney > 0;
                 residueMoney -= face, face = faces[residueMoney]) {
                sb.append(face).append(' ');
            }
            System.out.println(sb);
        }

        return lastCoin;
    }
}
