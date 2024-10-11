// url: https://leetcode.com/problems/longest-substring-without-repeating-characters/

import com.sun.jdi.CharType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {
    static class Solution {

        private static int getSubstringLength(String s) {
            Map<Character, Integer> collector = new HashMap<>();

            int output = 0, counter = 0;
            for (int i = 0; i < s.length(); i++) {
                char character = s.charAt(i);
                if (collector.containsKey(character)) {
                    i = collector.get(character);
                    if (counter > output) {
                        output = counter;
                    }
                    counter = 0;
                    collector.clear();
                } else {
                    collector.put(character, i);
                    counter++;
                }
            }
            if (counter > output) {
                output = counter;
            }
            return output;
        }

        public static void main(String[] args) {
//            String s = "abcabcbb";
//            String s = "bbbbbbbb";
//            String s = "pwwkew";
//            String s = "dvdf";
            String s = "aab";

            int length = getSubstringLength(s);

        }
    }
}

