// url: https://leetcode.com/problems/palindrome-number/

public class IsPalindrome {
    static class Solution {

        private boolean isPalindrome(int x) {
            String number = String.valueOf(x);
            int length = number.length();

            if (length == 1) {
                return true;
            }

            boolean isPolindrom = true;
            for (int i = 0; i < length / 2; i++) {
                if (number.charAt(i) != number.charAt(length - i - 1)) {
                    isPolindrom = false;
                }
            }

            return isPolindrom;
        }


        public static void main(String[] args) {
            int x = 121;
            Solution solution = new Solution();

            boolean result = solution.isPalindrome(x);
            System.out.println(result);
        }
    }
}
