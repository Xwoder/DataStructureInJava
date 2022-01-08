package Greedy;

import java.util.Arrays;

public class CoinChange1 {

    public static void main(String[] args) {
        Integer[] coinsFaceValue = new Integer[]{25, 5, 10, 1};
        Arrays.sort(coinsFaceValue, (Integer o1, Integer o2) -> o2 - o1);

        int money = 42;

        int numberOfCoins = 0;
        for (int i = 0; i < coinsFaceValue.length; ) {
            int coinFaceValue = coinsFaceValue[i];
            if (money < coinFaceValue) {
                i++;
                continue;
            }

            money -= coinFaceValue;
            System.out.println(coinFaceValue);
            ++numberOfCoins;
        }

        System.out.println(numberOfCoins);
    }
}
