// url: https://leetcode.com/problems/median-of-two-sorted-arrays/

import java.util.Arrays;

public class MedianOfTwoSortedArrays {
    static class Solution {

        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int[] concat = new int[nums1.length + nums2.length];

            System.arraycopy(nums1, 0, concat, 0, nums1.length);
            System.arraycopy(nums2, 0, concat, nums1.length, nums2.length);
            Arrays.sort(concat);

            double result = 0.0;
            if (concat.length % 2 == 1) {
                result = (double) concat[concat.length / 2];
            } else {
                int mid1 = concat[concat.length / 2 - 1];
                int mid2 = concat[concat.length / 2];
                result = (double) (mid1 + mid2) / 2.0;
            }
            return result;
        }

        public static void main(String[] args) {
//            int[] nums1 = {1, 3};
//            int[] nums2 = {2};

            int[] nums1 = {1, 2};
            int[] nums2 = {3, 4};

            Solution solution = new Solution();
            double result = solution.findMedianSortedArrays(nums1, nums2);
            System.out.println(result);
        }
    }
}
