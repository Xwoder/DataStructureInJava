package Greedy;

public class CoinChange2 {
    public static void main(String[] args) {
        int money = 41;

        int numberOfCoins = coinsForMoney(money);

        System.out.println(numberOfCoins);
    }

    private static int coinsForMoney(int money) {
        if (money <= 0) {
            return Integer.MAX_VALUE;
        }


        if (money == 25 || money == 20 || money == 10 || money == 1) {
            return 1;
        }

        int minNumberOfCoins1 = Math.min(coinsForMoney(money - 25), coinsForMoney(money - 20));
        int minNumberOfCoins2 = Math.min(coinsForMoney(money - 5), coinsForMoney(money - 1));
        return Math.min(minNumberOfCoins1, minNumberOfCoins2) + 1;
    }
}
