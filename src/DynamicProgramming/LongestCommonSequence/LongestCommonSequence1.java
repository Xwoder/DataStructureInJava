package DynamicProgramming.LongestCommonSequence;

/**
 * Longest Common Sequence 递归实现
 */
public class LongestCommonSequence1 {
    public static void main(String[] args) {
        LongestCommonSequence1 lcs = new LongestCommonSequence1();

        System.out.println(lcs.lcs(
                new int[]{1, 3, 5, 9, 10},
                new int[]{1, 4, 9, 19}));

        System.out.println(lcs.lcs(
                new int[]{1, 4, 5, 9, 10},
                new int[]{1, 4, 9, 10}));
    }

    private int lcs(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length <= 0
                || nums2 == null || nums2.length <= 0) {
            return 0;
        }

        return lcs(nums1, nums1.length, nums2, nums2.length);
    }

    /**
     * 求数组 num1 前 i 个元素 与数组 nums2 前 j 个元素的最大公共子序列长度
     */
    private int lcs(int[] nums1, int i, int[] nums2, int j) {
        if (nums1 == null || nums1.length <= 0
                || nums2 == null || nums2.length <= 0
                || i <= 0 || j <= 0) {
            return 0;
        }

        if (nums1[i - 1] == nums2[j - 1]) {
            return lcs(nums1, i - 1, nums2, j - 1) + 1;
        } else {

            return Math.max(
                    lcs(nums1, i - 1, nums2, j),
                    lcs(nums1, i , nums2, j - 1));
        }
    }
}
