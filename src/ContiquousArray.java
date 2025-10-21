// url: https://leetcode.com/problems/contiguous-array/description/

import java.util.HashMap;
import java.util.Map;

public class ContiquousArray {

    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> history = new HashMap<>();
        history.put(0, -1);
        int sum = 0, best = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i] == 1 ? 1 : -1;
            Integer j = history.get(sum);
            if (j != null) {
                best = Math.max(best, i - j);
            } else {
                history.put(sum, i);
            }
        }
        return best;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1};
        int result = new ContiquousArray().findMaxLength(nums);
        System.out.println(result);
    }
}
