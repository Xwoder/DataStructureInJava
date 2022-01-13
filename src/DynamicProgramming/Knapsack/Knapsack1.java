package DynamicProgramming.Knapsack;

public class Knapsack1 {

    public static void main(String[] args) {
        Knapsack1 knapsack1 = new Knapsack1();
        System.out.println(knapsack1.knapsack(
                new int[]{6, 3, 5, 4, 6},
                new int[]{2, 2, 6, 5, 4},
                10));

        System.out.println(knapsack1.knapsack(
                new int[]{4, 6},
                new int[]{1, 1},
                1));
    }

    public int knapsack(int[] values, int[] weights, int maxCapacity) {
        if (values == null || values.length <= 0
                || weights == null || weights.length <= 0
                || values.length != weights.length
                || maxCapacity == 0) {
            return 0;
        }

        /* 物品数量*/
        int numberOfItems = values.length;

        /* dp[i][j] 表示当最大承重为j、前i件物品可选时，背包能达到的最大价值 */
        int[][] dp = new int[numberOfItems + 1][maxCapacity + 1];

        for (int i = 1; i <= numberOfItems; i++) {
            for (int capacity = 1; capacity <= maxCapacity; capacity++) {
                if (weights[i - 1] > capacity) {
                    /* 最后一件物品的重量大于背包的最大承重，物品不可选 */
                    dp[i][capacity] = dp[i - 1][capacity];
                } else {
                    /* 最后一件物品的重量小于等于背包的最大承重，物品可选 */

                    /* 不放当前件物品时，背包的价值 */
                    int value1 = dp[i - 1][capacity];
                    /* 用当前物品取代上一件物品的时，背包的价值 */
                    int value2 = dp[i - 1][capacity - weights[i - 1]] + values[i - 1];
                    dp[i][capacity] = Math.max(value1, value2);
                }
            }
        }

        return dp[numberOfItems][maxCapacity];
    }
}
