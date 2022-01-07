package DivideAndConquer.MaximumSubarray;

public class MaximumSubarray3 {

    public static void main(String[] args) {
        int[] array = new int[]{-2, 0, -3, -3};

        int maxSum = maxSubArray(array);

        System.out.println(maxSum);
    }

    private static int maxSubArray(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        return maxSubArray(nums, 0, nums.length);
    }

    /**
     * 求数组的子数组的最大值
     * <p>
     * 数组元素的索引的取值范围是 [begin, end)
     *
     * @param nums  数组
     * @param begin 数组索引下限，含
     * @param end   数组索引的下限，不含
     * @return 子数组的最大值
     */
    private static int maxSubArray(int[] nums, int begin, int end) {
        if (end - begin <= 1) {
            return nums[begin];
        }

        int mid = (begin + end) >> 1;

        /*
         * 数组的左半边的最大和子序列
         * 由数组元素索引位于 [begin, mid) 内的元素构成的子数组的最大值，
         */
        int leftPartMax = maxSubArray(nums, begin, mid);

        /*
         * 数组的右半边的最大和子序列
         * 由数组元素索引位于 [mid, end) 内的元素构成的子数组的最大值
         */
        int rightPartMax = maxSubArray(nums, mid, end);

        int midLeftPartMax = nums[mid - 1];
        int midLeftPartSum = nums[mid - 1];
        for (int i = mid - 2; i >= begin; i--) {
            midLeftPartSum += nums[i];
            if (midLeftPartSum > midLeftPartMax) {
                midLeftPartMax = midLeftPartSum;
            }
        }
        int midRightPartMax = nums[mid];
        int midRightPartSum = nums[mid];
        for (int j = mid + 1; j < end; j++) {
            midRightPartSum += nums[j];
            if (midRightPartSum > midRightPartMax) {
                midRightPartMax = midRightPartSum;
            }
        }

        int leftAndRightMax = Math.max(leftPartMax, rightPartMax);
        int midPartMax = midLeftPartMax + midRightPartMax;

        return Math.max(leftAndRightMax, midPartMax);
    }
}
