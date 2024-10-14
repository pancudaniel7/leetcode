// url: https://leetcode.com/problems/longest-palindromic-substring/

public class LongestPalindromicSubstring {
    static class Solution {

        private static int expandAroundIndex(String s, int left, int right) {
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            return right - left - 1;
        }

        private static String getLongestPalindromicString(String s) {
            if (s == null || s.length() < 1) return "";
            int start = 0, end = 0;

            for (int i = 0; i < s.length(); i++) {
                int len1 = expandAroundIndex(s, i - 1, i + 1);
                int len2 = expandAroundIndex(s, i, i + 1);
                int len = Math.max(len1, len2);
                if (len > (end + 1 - start)) {
                    start = i - ((len - 1) / 2);
                    end = i + (len / 2);
                }
            }
            return s.substring(start, end + 1);
        }

        public static void main(String[] args) {
//            String s = "babad";
            String s = "cbbd";
            String output = getLongestPalindromicString(s);
            System.out.println(output);
        }
    }
}
