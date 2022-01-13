package DynamicProgramming.Knapsack;

public class Knapsack3 {

    public static void main(String[] args) {
        Knapsack3 knapsack1 = new Knapsack3();
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
            for (int capacity = maxCapacity; capacity >= weights[i - 1]; capacity--) {
                int value1 = dp[capacity];
                int value2 = dp[capacity - weights[i - 1]] + values[i - 1];
                dp[capacity] = Math.max(value1, value2);
            }
        }
        return dp[maxCapacity];
    }
}
