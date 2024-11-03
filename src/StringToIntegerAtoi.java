// https://leetcode.com/problems/string-to-integer-atoi/submissions/1441811674/

public class StringToIntegerAtoi {
    static class Solution {

        public int myAtoi(String s) {
            s = s.trim();
            if (s.isEmpty()) {
                return 0;
            }

            int sign = 1;
            int index = 0;
            if (s.charAt(0) == '-') {
                sign = -1;
                index++;
            } else if (s.charAt(0) == '+') {
                index++;
            }

            int result = 0;
            while (index < s.length()) {
                char currentChar = s.charAt(index);
                if (!Character.isDigit(currentChar)) {
                    break;
                }

                int digit = currentChar - '0';
                if (result > (Integer.MAX_VALUE - digit) / 10) {
                    return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }

                result = result * 10 + digit;
                index++;
            }

            return result * sign;
        }


        public static void main(String[] args) {
            String input = "1337c0d3";
//            String input = "-042";
//            String input = "0-1";
//            String input = "words and 987";
            Solution solution = new Solution();
            int result = solution.myAtoi(input);
            System.out.println(result);
        }
    }
}
