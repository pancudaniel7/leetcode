// url: https://leetcode.com/problems/regular-expression-matching/

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches {
    static class Solution {

        public boolean isMatch(String s, String p) {
            Pattern pattern = Pattern.compile(p);
            Matcher matcher = pattern.matcher(s);
            return matcher.matches();
        }

        public static void main(String[] args) {

            String pattern = "a*";
            String text = "aa";

            Solution solution = new Solution();
            boolean result = solution.isMatch(text, pattern);
            System.out.println(result);
        }
    }
}
