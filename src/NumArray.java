// url: https://leetcode.com/problems/range-sum-query-immutable/

import java.util.ArrayList;
import java.util.List;

public class NumArray {

    private List<Integer> sumIndexList;

    public NumArray(int[] nums) {
        sumIndexList = rangeSumFrom(nums);
    }

    private static List<Integer> rangeSumFrom(int[] currentArray) {
        List<Integer> sumIndexArray = new ArrayList<>();
        sumIndexArray.add(currentArray[0]);
        int sum = 0;
        for (int i = 0; i < currentArray.length; i++) {
            sum += currentArray[i];
            if (i != 0) {
                sumIndexArray.add(sum);
            }
        }
        return sumIndexArray;
    }

    public int sumRange(int left, int right) {
        if (left == 0) {
            return this.sumIndexList.get(right);
        }
        return this.sumIndexList.get(right) - this.sumIndexList.get(left - 1);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        int sum = new NumArray(nums).sumRange(2, 4);
        System.out.println(sum);
    }
}
