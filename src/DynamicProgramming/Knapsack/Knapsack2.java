package DynamicProgramming.Knapsack;

public class Knapsack2 {

    public static void main(String[] args) {
        Knapsack2 knapsack1 = new Knapsack2();
        System.out.println(knapsack1.knapsack(
                new int[]{6, 3, 5, 4, 6},
                new int[]{2, 2, 6, 5, 4},
                10));

        System.out.println(knapsack1.knapsack(new int[]{4, 6}, new int[]{1, 1}, 1));
    }

    public int knapsack(int[] values, int[] weights, int maxCapacity) {
        if (values == null || values.length <= 0 || weights == null || weights.length <= 0 || values.length != weights.length || maxCapacity == 0) {
            return 0;
        }

        int[] dp = new int[maxCapacity + 1];
        for (int i = 1; i <= values.length; i++) {
            for (int capacity = maxCapacity; capacity >= 1; capacity--) {
                if (capacity < weights[i - 1]) {
                    continue;
                }

                int value1 = dp[capacity];
                /* 用当前物品取代上一件物品的时，背包的价值 */
                int value2 = dp[capacity - weights[i - 1]] + values[i - 1];
                dp[capacity] = Math.max(value1, value2);
            }
        }
        return dp[maxCapacity];
    }
}
