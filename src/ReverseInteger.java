// url: https://leetcode.com/problems/reverse-integer/description/

public class ReverseInteger {
    static class Solution {

        public int reverse(int x) {
            boolean signed = false;

            if (x < 0) {
                signed = x < 0;
                x *= -1;
            }
            String reverse = new StringBuilder(String.valueOf(x)).reverse().toString();
            try {
                x = Integer.valueOf(reverse);
            } catch (Exception e) {
                return 0;
            }

            if (isOutsideTheLimits(x)) return 0;
            return (int) (signed ? x * -1 : x);
        }

        private static boolean isOutsideTheLimits(int x) {
            return x > Integer.MAX_VALUE || x < Integer.MAX_VALUE * -1;
        }

        public static void main(String[] args) {
//            int input = -2147483647;
//            int input = 1534236469;
            int input = -123;
            Solution solution = new Solution();
            int output = solution.reverse(input);
            System.out.println(output);
        }
    }
}
