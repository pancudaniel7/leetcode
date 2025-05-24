// url: https://leetcode.com/problems/3sum/

import javax.swing.*;
import java.util.*;

public class TreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> output = new ArrayList();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = nums.length - 1;
            int target = -nums[i];

            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    output.add(List.of(nums[i], nums[left], nums[right]));
                    left++;
                    right--;

                    // when we have left or right duplicated sequence values
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                } else {
                    if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return output;
    }

    public static void main(String[] args) {
        TreeSum treeSum = new TreeSum();
//        List<List<Integer>> result = treeSum.threeSum(new int[]{0, 1, 1});
//        List<List<Integer>> result = treeSum.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        List<List<Integer>> result = treeSum.threeSum(new int[]{-2,0,1,1,2});

        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.getFirst().size(); j++) {
                System.out.printf("%s ", result.get(i).get(j).toString());
            }
            System.out.println();
        }
    }
}
