// url: https://leetcode.com/problems/3sum-closest/

import java.util.Arrays;

public class TreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int output = 0;
        int diff = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int calDiff = Math.abs(target - sum);
                if (calDiff < diff) {
                    diff = calDiff;
                    output = sum;
                }

                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    return sum;
                }
            }
        }

        return output;
    }

    public static void main(String[] args) {
        TreeSumClosest treeSumClosest = new TreeSumClosest();
//        int output = treeSumClosest.threeSumClosest(new int[]{-1, 2, 1, -4, 1}, 1);
        int output = treeSumClosest.threeSumClosest(new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90}, 1);
        System.out.println(output);
    }
}
