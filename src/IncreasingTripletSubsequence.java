// url: https://leetcode.com/problems/increasing-triplet-subsequence/description/?envType=study-plan-v2&envId=leetcode-75
public class IncreasingTripletSubsequence {

    public boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num <= first) {
                first = num;
            } else if (num <= second) {
                second = num;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        IncreasingTripletSubsequence increasingTripletSubsequence = new IncreasingTripletSubsequence();
//        boolean result = increasingTripletSubsequence.increasingTriplet(new int[]{2, 1, 5, 0, 4, 6});
//        boolean result = increasingTripletSubsequence.increasingTriplet(new int[]{5, 4, 3, 2, 1});
//        boolean result = increasingTripletSubsequence.increasingTriplet(new int[]{5});
//        boolean result = increasingTripletSubsequence.increasingTriplet(new int[]{5, 4});
//        boolean result = increasingTripletSubsequence.increasingTriplet(new int[]{1, 2, 3});
//        boolean result = increasingTripletSubsequence.increasingTriplet(new int[]{1, 2, 5, 4});

//        boolean result = increasingTripletSubsequence.increasingTriplet(new int[]{20, 100, 10, 12, 5, 11, 13});
        boolean result = increasingTripletSubsequence.increasingTriplet(new int[]{20, 100, 21, 200});
        System.out.println(result);
    }
}
