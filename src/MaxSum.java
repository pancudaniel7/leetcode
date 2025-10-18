public class MaxSum {

    public int maxSum(int[] nums, int k) {
        if (nums.length < k) {
            return 0;
        }
        var sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int result = sum;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            if (sum > result) {
                result = sum;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int result = new MaxSum().maxSum(new int[]{4, 5, 9, 6, 6, 7, 3, 4}, 2);
        System.out.println(result);

        result = new MaxSum().maxSum(new int[]{4, 5, 9, 5, 6, 7, 3, 99}, 3);
        System.out.println(result);

        result = new MaxSum().maxSum(new int[]{4}, 3);
        System.out.println(result);
    }
}

