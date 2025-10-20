// url: https://leetcode.com/problems/longest-substring-without-repeating-characters/

import java.util.Arrays;

public class LongestSubstringWithoutRepeatingCharacters {
    static class Solution {

        private static int getSubstringLength(String s) {
            int[] last = new int[128];
            Arrays.fill(last, -1);
            int l = 0, ans = 0;
            for (int r = 0; r < s.length(); r++) {
                int c = s.charAt(r);
                if (last[c] >= l) {
                    l = last[c] + 1;
                }
                last[c] = r;
                ans = Math.max(ans, r - l + 1);
            }
            return ans;
        }

        public static void main(String[] args) {
//            String s = "abcabcbb";
//            String s = "bbbbbbbb";
            String s = "pwwkew";
//            String s = "dvdf";
//            String s = "aab";

            int length = getSubstringLength(s);
            System.out.println(length);
        }
    }
}

